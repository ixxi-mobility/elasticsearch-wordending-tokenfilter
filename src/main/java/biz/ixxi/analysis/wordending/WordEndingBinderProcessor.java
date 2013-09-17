package biz.ixxi.analysis.wordending;

import org.elasticsearch.index.analysis.AnalysisModule;

/**
*/
public class WordEndingBinderProcessor extends AnalysisModule.AnalysisBinderProcessor {

    @Override
    public void processTokenFilters(TokenFiltersBindings tokenFiltersBindings) {
        tokenFiltersBindings.processTokenFilter("wordending", WordEndingFilterFactory.class);
   }

}