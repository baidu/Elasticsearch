/*
 * Licensed to Elasticsearch under one or more contributor
 * license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright
 * ownership. Elasticsearch licenses this file to you under
 * the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.elasticsearch.cluster.metadata;

import org.elasticsearch.Version;
import org.elasticsearch.action.TimestampParsingException;
import org.elasticsearch.cluster.AbstractDiffable;
import org.elasticsearch.common.Nullable;
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.compress.CompressedXContent;
import org.elasticsearch.common.io.stream.StreamInput;
import org.elasticsearch.common.io.stream.StreamOutput;
import org.elasticsearch.common.joda.FormatDateTimeFormatter;
import org.elasticsearch.common.joda.Joda;
import org.elasticsearch.common.lucene.uid.Versions;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentHelper;
import org.elasticsearch.common.xcontent.XContentParser;
import org.elasticsearch.index.VersionType;
import org.elasticsearch.index.mapper.DocumentMapper;
import org.elasticsearch.index.mapper.MapperParsingException;
import org.elasticsearch.index.mapper.internal.TimestampFieldMapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import static org.elasticsearch.common.xcontent.support.XContentMapValues.nodeBooleanValue;

/**
 * Mapping configuration for a type.
 */
public class MappingMetaData extends AbstractDiffable<MappingMetaData> {

    public static final String MAPPING_VERSION = "_mapping_version";

    public static final MappingMetaData PROTO = new MappingMetaData();

    public static class Id {

        public static final Id EMPTY = new Id(null);

        private final String path;

        private final String[] pathElements;

        public Id(String path) {
            this.path = path;
            if (path == null) {
                pathElements = Strings.EMPTY_ARRAY;
            } else {
                pathElements = Strings.delimitedListToStringArray(path, ".");
            }
        }

        public boolean hasPath() {
            return path != null;
        }

        public String path() {
            return this.path;
        }

        public String[] pathElements() {
            return this.pathElements;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Id id = (Id) o;

            if (path != null ? !path.equals(id.path) : id.path != null) return false;
            if (!Arrays.equals(pathElements, id.pathElements)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = path != null ? path.hashCode() : 0;
            result = 31 * result + (pathElements != null ? Arrays.hashCode(pathElements) : 0);
            return result;
        }
    }

    public static class Routing {

        public static final Routing EMPTY = new Routing(false, null);

        private final boolean required;

        private final String path;

        private final String[] pathElements;

        public Routing(boolean required, String path) {
            this.required = required;
            this.path = path;
            if (path == null) {
                pathElements = Strings.EMPTY_ARRAY;
            } else {
                pathElements = Strings.delimitedListToStringArray(path, ".");
            }
        }

        public boolean required() {
            return required;
        }

        public boolean hasPath() {
            return path != null;
        }

        public String path() {
            return this.path;
        }

        public String[] pathElements() {
            return this.pathElements;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Routing routing = (Routing) o;

            if (required != routing.required) return false;
            if (path != null ? !path.equals(routing.path) : routing.path != null) return false;
            if (!Arrays.equals(pathElements, routing.pathElements)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = (required ? 1 : 0);
            result = 31 * result + (path != null ? path.hashCode() : 0);
            result = 31 * result + (pathElements != null ? Arrays.hashCode(pathElements) : 0);
            return result;
        }
    }

    public static class Timestamp {

        private static final FormatDateTimeFormatter EPOCH_MILLIS_PARSER = Joda.forPattern("epoch_millis");

        public static String parseStringTimestamp(String timestampAsString, FormatDateTimeFormatter dateTimeFormatter,
                                                  Version version) throws TimestampParsingException {
            try {
                // no need for unix timestamp parsing in 2.x
                FormatDateTimeFormatter formatter = version.onOrAfter(Version.V_2_0_0_beta1) ? dateTimeFormatter : EPOCH_MILLIS_PARSER;
                return Long.toString(formatter.parser().parseMillis(timestampAsString));
            } catch (RuntimeException e) {
                if (version.before(Version.V_2_0_0_beta1)) {
                    try {
                        return Long.toString(dateTimeFormatter.parser().parseMillis(timestampAsString));
                    } catch (RuntimeException e1) {
                        throw new TimestampParsingException(timestampAsString, e1);
                    }
                }
                throw new TimestampParsingException(timestampAsString, e);
            }
        }


        public static final Timestamp EMPTY = new Timestamp(false, null, TimestampFieldMapper.DEFAULT_DATE_TIME_FORMAT,
                TimestampFieldMapper.Defaults.DEFAULT_TIMESTAMP, null);

        private final boolean enabled;

        private final String path;

        private final String format;

        private final String[] pathElements;

        private final FormatDateTimeFormatter dateTimeFormatter;

        private final String defaultTimestamp;

        private final Boolean ignoreMissing;

        public Timestamp(boolean enabled, String path, String format, String defaultTimestamp, Boolean ignoreMissing) {
            this.enabled = enabled;
            this.path = path;
            if (path == null) {
                pathElements = Strings.EMPTY_ARRAY;
            } else {
                pathElements = Strings.delimitedListToStringArray(path, ".");
            }
            this.format = format;
            this.dateTimeFormatter = Joda.forPattern(format);
            this.defaultTimestamp = defaultTimestamp;
            this.ignoreMissing = ignoreMissing;
        }

        public boolean enabled() {
            return enabled;
        }

        public boolean hasPath() {
            return path != null;
        }

        public String path() {
            return this.path;
        }

        public String[] pathElements() {
            return this.pathElements;
        }

        public String format() {
            return this.format;
        }

        public String defaultTimestamp() {
            return this.defaultTimestamp;
        }

        public boolean hasDefaultTimestamp() {
            return this.defaultTimestamp != null;
        }

        public Boolean ignoreMissing() {
            return ignoreMissing;
        }

        public FormatDateTimeFormatter dateTimeFormatter() {
            return this.dateTimeFormatter;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Timestamp timestamp = (Timestamp) o;

            if (enabled != timestamp.enabled) return false;
            if (format != null ? !format.equals(timestamp.format) : timestamp.format != null) return false;
            if (path != null ? !path.equals(timestamp.path) : timestamp.path != null) return false;
            if (defaultTimestamp != null ? !defaultTimestamp.equals(timestamp.defaultTimestamp) : timestamp.defaultTimestamp != null) return false;
            if (ignoreMissing != null ? !ignoreMissing.equals(timestamp.ignoreMissing) : timestamp.ignoreMissing != null) return false;
            if (!Arrays.equals(pathElements, timestamp.pathElements)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = (enabled ? 1 : 0);
            result = 31 * result + (path != null ? path.hashCode() : 0);
            result = 31 * result + (format != null ? format.hashCode() : 0);
            result = 31 * result + (pathElements != null ? Arrays.hashCode(pathElements) : 0);
            result = 31 * result + (dateTimeFormatter != null ? dateTimeFormatter.hashCode() : 0);
            result = 31 * result + (defaultTimestamp != null ? defaultTimestamp.hashCode() : 0);
            result = 31 * result + (ignoreMissing != null ? ignoreMissing.hashCode() : 0);
            return result;
        }
    }
    public static class ParsedVersion {
        
        public static final ParsedVersion EMPTY = new ParsedVersion(null, VersionType.INTERNAL);
        private final String path;
        private final VersionType versionType;
        private final String[] pathElements;

        public ParsedVersion(String path, String versionType) {
            this.path = path;
            if (path == null) {
                this.pathElements = Strings.EMPTY_ARRAY;
                this.versionType = VersionType.INTERNAL;
            } else {
                this.versionType = VersionType.fromString(versionType);
                this.pathElements = Strings.delimitedListToStringArray(path, ".");
            }
        }
        
        public ParsedVersion(String path, VersionType versionType) {
            this.path = path;
            if (path == null) {
                this.pathElements = Strings.EMPTY_ARRAY;
                this.versionType = VersionType.INTERNAL;
            } else {
                this.versionType = versionType;
                this.pathElements = Strings.delimitedListToStringArray(path, ".");
            }
        }
        
        public boolean hasPath () {
            return path != null;
        }
        
        public String[] pathElements() {
            return this.pathElements;
        }
        
        public String path() {
            return path;
        }
        
        public VersionType versionType() {
            return this.versionType;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((path == null) ? 0 : path.hashCode());
            result = prime * result + ((versionType == null) ? 0 : versionType.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            ParsedVersion other = (ParsedVersion) obj;
            if (path == null) {
                if (other.path != null)
                    return false;
            } else if (!path.equals(other.path))
                return false;
            if (versionType != other.versionType)
                return false;
            return true;
        }
    }
    private final String type;

    private final CompressedXContent source;

    private Id id;
    private Routing routing;
    private Timestamp timestamp;
    private ParsedVersion version;
    private boolean hasParentField;
    private long mappingVersion;

    public long mappingVersion() {
        return this.mappingVersion;
    }

    public void setMappingVersion(long version) {
        this.mappingVersion = version;
    }

    public void mappingVersionIncrement() {
        this.mappingVersion += 1;
    }

    public MappingMetaData(DocumentMapper docMapper) {
        this(docMapper, 1);
    }

    public MappingMetaData(DocumentMapper docMapper, long mappingVersion) {
        this.type = docMapper.type();
        this.source = docMapper.mappingSource();
        this.id = new Id(docMapper.idFieldMapper().path());
        this.routing = new Routing(docMapper.routingFieldMapper().required(), docMapper.routingFieldMapper().path());
        this.timestamp = new Timestamp(docMapper.timestampFieldMapper().enabled(), docMapper.timestampFieldMapper().path(),
                docMapper.timestampFieldMapper().fieldType().dateTimeFormatter().format(), docMapper.timestampFieldMapper().defaultTimestamp(),
                docMapper.timestampFieldMapper().ignoreMissing());
        this.version = new ParsedVersion(docMapper.versionFieldMapper().path(), docMapper.versionFieldMapper().VersionType());
        this.hasParentField = docMapper.parentFieldMapper().active();
        this.mappingVersion = mappingVersion;
    }

    public MappingMetaData(CompressedXContent mapping) throws IOException {
        Map<String, Object> mappingMap;
        try (XContentParser parser = XContentHelper.createParser(mapping.compressedReference())) {
            mappingMap = parser.mapOrdered();
        }
        if (mappingMap.containsKey(MAPPING_VERSION)) {
            this.mappingVersion = (int)mappingMap.get(MAPPING_VERSION);
            mappingMap.remove(MAPPING_VERSION);
        } else {
            this.mappingVersion = 1;
        }
        XContentBuilder mappingBuilder = XContentFactory.jsonBuilder().map(mappingMap);
        this.source = new CompressedXContent(mappingBuilder.bytes());
        if (mappingMap.size() != 1) {
            throw new IllegalStateException("Can't derive type from mapping, no root type: " + mapping.string());
        }
        this.type = mappingMap.keySet().iterator().next();
        initMappers((Map<String, Object>) mappingMap.get(this.type));
    }

    public MappingMetaData(Map<String, Object> mapping, long mappingVersion) throws IOException {
        this(mapping.keySet().iterator().next(), mapping, mappingVersion);
    }

    public MappingMetaData(String type, Map<String, Object> mapping, long mappingVersion) throws IOException {
        this.type = type;
        this.mappingVersion = mappingVersion;
        XContentBuilder mappingBuilder = XContentFactory.jsonBuilder().map(mapping);
        this.source = new CompressedXContent(mappingBuilder.bytes());
        Map<String, Object> withoutType = mapping;
        if (mapping.size() == 1 && mapping.containsKey(type)) {
            withoutType = (Map<String, Object>) mapping.get(type);
        }
        initMappers(withoutType);
    }

    private MappingMetaData() {
        this.type = "";
        this.mappingVersion = 1;
        try {
            this.source = new CompressedXContent("{}");
        } catch (IOException ex) {
            throw new IllegalStateException("Cannot create MappingMetaData prototype", ex);
        }
    }

    private void initMappers(Map<String, Object> withoutType) {
        if (withoutType.containsKey("_id")) {
            String path = null;
            Map<String, Object> routingNode = (Map<String, Object>) withoutType.get("_id");
            for (Map.Entry<String, Object> entry : routingNode.entrySet()) {
                String fieldName = Strings.toUnderscoreCase(entry.getKey());
                Object fieldNode = entry.getValue();
                if (fieldName.equals("path")) {
                    path = fieldNode.toString();
                }
            }
            this.id = new Id(path);
        } else {
            this.id = Id.EMPTY;
        }
        if (withoutType.containsKey("_routing")) {
            boolean required = false;
            String path = null;
            Map<String, Object> routingNode = (Map<String, Object>) withoutType.get("_routing");
            for (Map.Entry<String, Object> entry : routingNode.entrySet()) {
                String fieldName = Strings.toUnderscoreCase(entry.getKey());
                Object fieldNode = entry.getValue();
                if (fieldName.equals("required")) {
                    required = nodeBooleanValue(fieldNode);
                } else if (fieldName.equals("path")) {
                    path = fieldNode.toString();
                }
            }
            this.routing = new Routing(required, path);
        } else {
            this.routing = Routing.EMPTY;
        }
        if (withoutType.containsKey("_timestamp")) {
            boolean enabled = false;
            String path = null;
            String format = TimestampFieldMapper.DEFAULT_DATE_TIME_FORMAT;
            String defaultTimestamp = TimestampFieldMapper.Defaults.DEFAULT_TIMESTAMP;
            Boolean ignoreMissing = null;
            Map<String, Object> timestampNode = (Map<String, Object>) withoutType.get("_timestamp");
            for (Map.Entry<String, Object> entry : timestampNode.entrySet()) {
                String fieldName = Strings.toUnderscoreCase(entry.getKey());
                Object fieldNode = entry.getValue();
                if (fieldName.equals("enabled")) {
                    enabled = nodeBooleanValue(fieldNode);
                } else if (fieldName.equals("path")) {
                    path = fieldNode.toString();
                } else if (fieldName.equals("format")) {
                    format = fieldNode.toString();
                } else if (fieldName.equals("default") && fieldNode != null) {
                    defaultTimestamp = fieldNode.toString();
                } else if (fieldName.equals("ignore_missing")) {
                    ignoreMissing = nodeBooleanValue(fieldNode);
                }
            }
            this.timestamp = new Timestamp(enabled, path, format, defaultTimestamp, ignoreMissing);
        } else {
            this.timestamp = Timestamp.EMPTY;
        }
        
        if (withoutType.containsKey("_version")) {
            String path = null;
            String versionType = null;
            Map<String, Object> versionNode = (Map<String, Object>) withoutType.get("_version");
            for (Map.Entry<String, Object> entry : versionNode.entrySet()) {
                String fieldName = Strings.toUnderscoreCase(entry.getKey());
                Object fieldNode = entry.getValue();
                if (fieldName.equals("path")) {
                    path = fieldNode.toString();
                } else if (fieldName.equals("version_type")) {
                    versionType = fieldNode.toString();
                }
            }
            this.version = new ParsedVersion(path, versionType);
        } else {
            this.version = ParsedVersion.EMPTY;
        }
        
        if (withoutType.containsKey("_parent")) {
            this.hasParentField = true;
        } else {
            this.hasParentField = false;
        }
    }

    public MappingMetaData(String type, CompressedXContent source, Id id, Routing routing, Timestamp timestamp,
            boolean hasParentField, ParsedVersion version, long mappingVersion) {
        this.type = type;
        this.source = source;
        this.id = id;
        this.routing = routing;
        this.timestamp = timestamp;
        this.hasParentField = hasParentField;
        this.version = version;
        this.mappingVersion = mappingVersion;
    }

    void updateDefaultMapping(MappingMetaData defaultMapping) {
        if (id == Id.EMPTY) {
            id = defaultMapping.id();
        }
        if (routing == Routing.EMPTY) {
            routing = defaultMapping.routing();
        }
        if (timestamp == Timestamp.EMPTY) {
            timestamp = defaultMapping.timestamp();
        }
    }

    public String type() {
        return this.type;
    }

    public CompressedXContent source() {
        return this.source;
    }

    public boolean hasParentField() {
        return hasParentField;
    }

    /**
     * Converts the serialized compressed form of the mappings into a parsed map.
     */
    public Map<String, Object> sourceAsMap() throws IOException {
        Map<String, Object> mapping = XContentHelper.convertToMap(source.compressedReference(), true).v2();
        if (mapping.size() == 1 && mapping.containsKey(type())) {
            // the type name is the root value, reduce it
            mapping = (Map<String, Object>) mapping.get(type());
        }
        // mapping.put(MAPPING_VERSION, mappingVersion);
        return mapping;
    }

    /**
     * Converts the serialized compressed form of the mappings into a parsed map.
     */
    public Map<String, Object> getSourceAsMap() throws IOException {
        return sourceAsMap();
    }

    public Id id() {
        return this.id;
    }

    public Routing routing() {
        return this.routing;
    }

    public Timestamp timestamp() {
        return this.timestamp;
    }

    public ParsedVersion version() {
        return this.version;
    }
    
    public ParseContext createParseContext(@Nullable String id, @Nullable String routing, @Nullable String timestamp, long version) {
        // We parse the routing even if there is already a routing key in the request in order to make sure that
        // they are the same
        return new ParseContext(
                id == null && id().hasPath(),
                routing().hasPath(),
                timestamp == null && timestamp().hasPath(),
                version == Versions.MATCH_ANY && version().hasPath()
        );
    }

    public void parse(XContentParser parser, ParseContext parseContext) throws IOException {
        innerParse(parser, parseContext);
    }

    private void innerParse(XContentParser parser, ParseContext context) throws IOException {
        if (!context.parsingStillNeeded()) {
            return;
        }

        XContentParser.Token token = parser.currentToken();
        if (token == null) {
            token = parser.nextToken();
        }
        if (token == XContentParser.Token.START_OBJECT) {
            token = parser.nextToken();
        }
        String idPart = context.idParsingStillNeeded() ? id().pathElements()[context.locationId] : null;
        String routingPart = context.routingParsingStillNeeded() ? routing().pathElements()[context.locationRouting] : null;
        String timestampPart = context.timestampParsingStillNeeded() ? timestamp().pathElements()[context.locationTimestamp] : null;
        String versionPart = context.versionParsingStillNeeded() ? version().pathElements()[context.locationVersion] : null;
        
        for (; token == XContentParser.Token.FIELD_NAME; token = parser.nextToken()) {
            // Must point to field name
            String fieldName = parser.currentName();
            // And then the value...
            token = parser.nextToken();
            boolean incLocationId = false;
            boolean incLocationRouting = false;
            boolean incLocationTimestamp = false;
            boolean incLocationVersion = false;
            if (context.idParsingStillNeeded() && fieldName.equals(idPart)) {
                if (context.locationId + 1 == id.pathElements().length) {
                    if (!token.isValue()) {
                        throw new MapperParsingException("id field must be a value but was either an object or an array");
                    }
                    context.id = parser.textOrNull();
                    context.idResolved = true;
                } else {
                    incLocationId = true;
                }
            }
            if (context.routingParsingStillNeeded() && fieldName.equals(routingPart)) {
                if (context.locationRouting + 1 == routing.pathElements().length) {
                    context.routing = parser.textOrNull();
                    context.routingResolved = true;
                } else {
                    incLocationRouting = true;
                }
            }
            if (context.timestampParsingStillNeeded() && fieldName.equals(timestampPart)) {
                if (context.locationTimestamp + 1 == timestamp.pathElements().length) {
                    context.timestamp = parser.textOrNull();
                    context.timestampResolved = true;
                } else {
                    incLocationTimestamp = true;
                }
            }
            
            if (context.versionParsingStillNeeded() && fieldName.equals(versionPart)) {
                if (context.locationVersion + 1 == version.pathElements().length) {
                    context.version = parser.textOrNull();
                    context.versionResolved = true;
                } else {
                    incLocationVersion = true;
                }
            }
            if (incLocationId || incLocationRouting || incLocationTimestamp || incLocationVersion) {
                if (token == XContentParser.Token.START_OBJECT) {
                    context.locationId += incLocationId ? 1 : 0;
                    context.locationRouting += incLocationRouting ? 1 : 0;
                    context.locationTimestamp += incLocationTimestamp ? 1 : 0;
                    context.locationVersion += incLocationVersion ? 1 : 0;
                    innerParse(parser, context);
                    context.locationId -= incLocationId ? 1 : 0;
                    context.locationRouting -= incLocationRouting ? 1 : 0;
                    context.locationTimestamp -= incLocationTimestamp ? 1 : 0;
                }
            } else {
                parser.skipChildren();
            }

            if (!context.parsingStillNeeded()) {
                return;
            }
        }
    }

    @Override
    public void writeTo(StreamOutput out) throws IOException {
        out.writeString(type());
        source().writeTo(out);
        // id
        if (id().hasPath()) {
            out.writeBoolean(true);
            out.writeString(id().path());
        } else {
            out.writeBoolean(false);
        }
        // routing
        out.writeBoolean(routing().required());
        if (routing().hasPath()) {
            out.writeBoolean(true);
            out.writeString(routing().path());
        } else {
            out.writeBoolean(false);
        }
        // timestamp
        out.writeBoolean(timestamp().enabled());
        out.writeOptionalString(timestamp().path());
        out.writeString(timestamp().format());
        out.writeOptionalString(timestamp().defaultTimestamp());
        out.writeOptionalBoolean(timestamp().ignoreMissing());
        out.writeBoolean(hasParentField());
        // mappingVersion
        out.writeLong(mappingVersion());

        if (version().hasPath()) {
            out.writeBoolean(true);
            out.writeString(version().path());
            out.writeByte(version().versionType().getValue());
        } else {
            out.writeBoolean(false);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MappingMetaData that = (MappingMetaData) o;

        if (!id.equals(that.id)) return false;
        if (!routing.equals(that.routing)) return false;
        if (!source.equals(that.source)) return false;
        if (!timestamp.equals(that.timestamp)) return false;
        if (!type.equals(that.type)) return false;
        if (!version.equals(that.version)) return false;

        if (mappingVersion != that.mappingVersion) return false;

        return true;
    }

    @Override
    public int hashCode() {
        Long mappingVersion = new Long(this.mappingVersion);
        int result = type.hashCode();
        result = 31 * result + source.hashCode();
        result = 31 * result + id.hashCode();
        result = 31 * result + routing.hashCode();
        result = 31 * result + timestamp.hashCode();
        result = 31 * result + version.hashCode();
        result = 31 * result + mappingVersion.hashCode();
        return result;
    }

    public MappingMetaData readFrom(StreamInput in) throws IOException {
        String type = in.readString();
        CompressedXContent source = CompressedXContent.readCompressedString(in);
        // id
        Id id = new Id(in.readBoolean() ? in.readString() : null);
        // routing
        Routing routing = new Routing(in.readBoolean(), in.readBoolean() ? in.readString() : null);
        // timestamp

        boolean enabled = in.readBoolean();
        String path = in.readOptionalString();
        String format = in.readString();
        String defaultTimestamp = in.readOptionalString();
        Boolean ignoreMissing = null;

        ignoreMissing = in.readOptionalBoolean();

        final Timestamp timestamp = new Timestamp(enabled, path, format, defaultTimestamp, ignoreMissing);
        final boolean hasParentField = in.readBoolean();
        final long mappingVersion = in.readLong();

        ParsedVersion version = new ParsedVersion(null, VersionType.INTERNAL);
        boolean hasVersionPath = in.readBoolean();
        if (hasVersionPath) {
            String versionPath = in.readString();
            VersionType versionType = VersionType.fromValue(in.readByte());
            version = new ParsedVersion(versionPath, versionType);
        }
        return new MappingMetaData(type, source, id, routing, timestamp, hasParentField, version, mappingVersion);
    }

    public static class ParseContext {
        final boolean shouldParseId;
        final boolean shouldParseRouting;
        final boolean shouldParseTimestamp;
        final boolean shouldParseVersion;

        int locationId = 0;
        int locationRouting = 0;
        int locationTimestamp = 0;
        int locationVersion = 0;
        boolean idResolved;
        boolean routingResolved;
        boolean timestampResolved;
        boolean versionResolved;
        String id;
        String routing;
        String timestamp;
        String version;

        public ParseContext(boolean shouldParseId, boolean shouldParseRouting, boolean shouldParseTimestamp, boolean shouldParseVersion) {
            this.shouldParseId = shouldParseId;
            this.shouldParseRouting = shouldParseRouting;
            this.shouldParseTimestamp = shouldParseTimestamp;
            this.shouldParseVersion = shouldParseVersion;
        }

        /**
         * The id value parsed, <tt>null</tt> if does not require parsing, or not resolved.
         */
        public String id() {
            return id;
        }

        /**
         * Does id parsing really needed at all?
         */
        public boolean shouldParseId() {
            return shouldParseId;
        }

        /**
         * Has id been resolved during the parsing phase.
         */
        public boolean idResolved() {
            return idResolved;
        }

        /**
         * Is id parsing still needed?
         */
        public boolean idParsingStillNeeded() {
            return shouldParseId && !idResolved;
        }

        /**
         * The routing value parsed, <tt>null</tt> if does not require parsing, or not resolved.
         */
        public String routing() {
            return routing;
        }

        /**
         * Does routing parsing really needed at all?
         */
        public boolean shouldParseRouting() {
            return shouldParseRouting;
        }

        /**
         * Has routing been resolved during the parsing phase.
         */
        public boolean routingResolved() {
            return routingResolved;
        }

        /**
         * Is routing parsing still needed?
         */
        public boolean routingParsingStillNeeded() {
            return shouldParseRouting && !routingResolved;
        }

        /**
         * The timestamp value parsed, <tt>null</tt> if does not require parsing, or not resolved.
         */
        public String timestamp() {
            return timestamp;
        }

        /**
         * Does timestamp parsing really needed at all?
         */
        public boolean shouldParseTimestamp() {
            return shouldParseTimestamp;
        }

        /**
         * Has timestamp been resolved during the parsing phase.
         */
        public boolean timestampResolved() {
            return timestampResolved;
        }

        /**
         * Is timestamp parsing still needed?
         */
        public boolean timestampParsingStillNeeded() {
            return shouldParseTimestamp && !timestampResolved;
        }

        /**
         * Do we really need parsing?
         */
        public boolean shouldParse() {
            return shouldParseId || shouldParseRouting || shouldParseTimestamp || shouldParseVersion;
        }

        /**
         * Is parsing still needed?
         */
        public boolean parsingStillNeeded() {
            return idParsingStillNeeded() || routingParsingStillNeeded() || timestampParsingStillNeeded() || versionParsingStillNeeded();
        }
        

        /**
         * The routing value parsed, <tt>null</tt> if does not require parsing, or not resolved.
         */
        public String version() {
            return version;
        }

        /**
         * Does routing parsing really needed at all?
         */
        public boolean shouldParseVersion() {
            return shouldParseVersion;
        }

        /**
         * Has routing been resolved during the parsing phase.
         */
        public boolean versionResolved() {
            return versionResolved;
        }

        /**
         * Is routing parsing still needed?
         */
        public boolean versionParsingStillNeeded() {
            return shouldParseVersion && !versionResolved;
        }
    }
}
