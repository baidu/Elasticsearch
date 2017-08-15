/*
 * Copyright (c) 2017 Baidu, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.elasticsearch.index.mapper.internal;

import org.apache.lucene.document.Field;
import org.apache.lucene.document.NumericDocValuesField;
import org.apache.lucene.index.DocValuesType;
import org.apache.lucene.index.IndexOptions;
import org.elasticsearch.Version;
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.index.analysis.NumericLongAnalyzer;
import org.elasticsearch.index.fielddata.FieldDataType;
import org.elasticsearch.index.mapper.MappedFieldType;
import org.elasticsearch.index.mapper.Mapper;
import org.elasticsearch.index.mapper.MapperParsingException;
import org.elasticsearch.index.mapper.MetadataFieldMapper;
import org.elasticsearch.index.mapper.ParseContext;
import org.elasticsearch.index.mapper.ParseContext.Document;
import org.elasticsearch.index.mapper.core.LongFieldMapper;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/** Mapper for the _mapping_version field. */
public class MappingVersionFieldMapper extends MetadataFieldMapper {

    public static final String NAME = "_mapping_version";
    public static final String CONTENT_TYPE = "_mapping_version";

    public static class Defaults extends LongFieldMapper.Defaults {

        public static final String NAME = MappingVersionFieldMapper.NAME;
        public static final MappedFieldType FIELD_TYPE = new MappingVersionFieldType();

        static {
            FIELD_TYPE.setIndexOptions(IndexOptions.DOCS);
            FIELD_TYPE.setStored(true);
            FIELD_TYPE.setTokenized(false);
            FIELD_TYPE.setNumericPrecisionStep(Defaults.PRECISION_STEP_64_BIT);
            FIELD_TYPE.setIndexAnalyzer(NumericLongAnalyzer.buildNamedAnalyzer(Defaults.PRECISION_STEP_64_BIT));
            FIELD_TYPE.setSearchAnalyzer(NumericLongAnalyzer.buildNamedAnalyzer(Integer.MAX_VALUE));
            FIELD_TYPE.setNames(new MappedFieldType.Names(NAME));
            FIELD_TYPE.freeze();
        }
    }

    public static class Builder extends MetadataFieldMapper.Builder<Builder, MappingVersionFieldMapper> {

        public Builder() {
            super(Defaults.NAME, Defaults.FIELD_TYPE, Defaults.FIELD_TYPE);
        }

        @Override
        public MappingVersionFieldMapper build(BuilderContext context) {
            return new MappingVersionFieldMapper(context.indexSettings());
        }
    }

    public static class TypeParser implements MetadataFieldMapper.TypeParser {
        @Override
        public MetadataFieldMapper.Builder<?, ?> parse(String name, Map<String, Object> node, ParserContext parserContext) throws MapperParsingException {
            Builder builder = new Builder();
            for (Iterator<Map.Entry<String, Object>> iterator = node.entrySet().iterator(); iterator.hasNext();) {
                Map.Entry<String, Object> entry = iterator.next();
                String fieldName = Strings.toUnderscoreCase(entry.getKey());
                if (fieldName.equals("doc_values_format") && parserContext.indexVersionCreated().before(Version.V_2_0_0_beta1)) {
                    // ignore in 1.x, reject in 2.x
                    iterator.remove();
                }
            }
            return builder;
        }

        @Override
        public MetadataFieldMapper getDefault(Settings indexSettings, MappedFieldType fieldType, String typeName) {
            return new MappingVersionFieldMapper(indexSettings);
        }
    }

    static final class MappingVersionFieldType extends LongFieldMapper.LongFieldType {

        public MappingVersionFieldType() {}

        protected MappingVersionFieldType(MappingVersionFieldType ref) {
            super(ref);
        }

        @Override
        public MappingVersionFieldType clone() {
            return new MappingVersionFieldType(this);
        }

        /**
         * Override the default behavior to return a timestamp
         */
        @Override
        public Object valueForSearch(Object value) {
            return value(value);
        }

        @Override
        public Long value(Object value) {
            if (value == null || (value instanceof Long)) {
                return (Long) value;
            } else {
                return Long.parseLong(value.toString());
            }
        }
    }

    private MappingVersionFieldMapper(Settings indexSettings) {
        super(NAME, Defaults.FIELD_TYPE, Defaults.FIELD_TYPE, indexSettings);
    }

    @Override
    public void preParse(ParseContext context) throws IOException {
        super.parse(context);
    }

    @Override
    protected void parseCreateField(ParseContext context, List<Field> fields) throws IOException {
        // see Engine.updateVersion to see where the real mappingVersion value is set
        final Field mappingVersion = new LongFieldMapper.CustomLongNumericField(context.sourceToParse().mappingVersion(), fieldType());
        fields.add(mappingVersion);
    }

    @Override
    public Mapper parse(ParseContext context) throws IOException {
        // _mapping_version added in preparse
        return null;
    }

    @Override
    public void postParse(ParseContext context) throws IOException {
    }

    @Override
    protected String contentType() {
        return CONTENT_TYPE;
    }

    @Override
    public XContentBuilder toXContent(XContentBuilder builder, Params params) throws IOException {
        return builder;
    }

    @Override
    protected void doMerge(Mapper mergeWith, boolean updateAllTypes) {
        // nothing to do
    }
}
