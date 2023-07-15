package de.uniba.minf.ir.part1;

import de.uniba.minf.ir.common.csv.stackoverflow.StackOverflowEntrySurrogate;
import de.uniba.minf.ir.common.solr.PojoConverterFunction;
import org.apache.solr.client.solrj.beans.Field;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public final class BeanCreatorJ implements PojoConverterFunction<StackOverflowEntrySurrogate, Object> {
    @SuppressWarnings("unused")
    public BeanCreatorJ() { /* DO NOT CHANGE THIS */ }


    public class Bean {

        @Field
        public String id;
        @Field
        public String title;
        @Field
        public String body;
        @Field
        public List<String> tags;

        public Bean() {
        }


        public Bean(String id, String title, String body, List<String> tags) {
            this.id = replaceNull(id, UUID.randomUUID().toString());
            this.title = replaceNull(title, "");
            this.body = replaceNull(body, "");
            this.tags = replaceNull(tags, new LinkedList<>());
        }

        private <T extends Object> T replaceNull(T maybeNull, T def) {
            if (maybeNull != null) return maybeNull;
            return def;
        }

    }


    @NotNull
    @Override
    public Object convertToPojo(StackOverflowEntrySurrogate toConvert) {
        String id = UUID.randomUUID().toString();
        List tags = toConvert.getTags();
        String body = toConvert.getBody();
        String title = toConvert.getTitle();

        return new Bean(id, title, body, tags);

    }
}
