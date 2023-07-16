package de.uniba.minf.ir.part2;


import de.uniba.minf.ir.common.solr.SolrQueryUnion;
import de.uniba.minf.ir.common.solr.SolrQueryUnionProvider;
import kotlin.NotImplementedError;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.request.json.JsonQueryRequest;
import org.apache.solr.common.params.MapSolrParams;
import org.apache.solr.common.params.SolrParams;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * @see SolrQueryUnion for hints how to implement
 */
enum JQueryProvider implements SolrQueryUnionProvider {
    Query01 {
        @NotNull
        @Override
        public SolrQueryUnion provide() {

            SolrQuery query = new SolrQuery("body:\"java sql\"~5");
            return SolrQueryUnion.wrap(query);
        }
    },
    Query02 {
        @NotNull
        @Override
        public SolrQueryUnion provide() {
            SolrQuery query = new SolrQuery("body:\"*java class*\"");
            return SolrQueryUnion.wrap(query);
        }
    }
}