package org.revo.Domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.lucene.analysis.charfilter.MappingCharFilterFactory;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.core.StopFilterFactory;
import org.apache.lucene.analysis.miscellaneous.ASCIIFoldingFilterFactory;
import org.apache.lucene.analysis.ngram.NGramFilterFactory;
import org.apache.lucene.analysis.standard.StandardFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.hibernate.search.annotations.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by ashraf on 24/02/17.
 */
@Data
@Entity
@Indexed
@Builder
@NoArgsConstructor
@AllArgsConstructor
@AnalyzerDef(name="customanalyzer",
        tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
        filters = {
                @TokenFilterDef(factory = LowerCaseFilterFactory.class),
                @TokenFilterDef(factory = NGramFilterFactory.class,params = {
                        @Parameter(name = "minGramSize",value="3"),
                        @Parameter(name = "maxGramSize",value="3")
                }),
                @TokenFilterDef(factory = StopFilterFactory.class, params = {
                        @Parameter(name="ignoreCase", value="true")
                })
        })
public class Product {
    private
    @Id
    @GeneratedValue
    Long id;
    private
    @Field(store = Store.YES)
    @Analyzer(definition = "customanalyzer")
    String name;
    private
    @Field(index = Index.NO, store = Store.YES)
    String url;

}
