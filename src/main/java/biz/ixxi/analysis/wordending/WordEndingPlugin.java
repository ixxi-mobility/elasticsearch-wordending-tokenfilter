package biz.ixxi.analysis.wordending;

//import com.google.common.collect.ImmutableList;
import org.elasticsearch.common.collect.ImmutableList;
import org.elasticsearch.common.inject.Module;
import org.elasticsearch.index.analysis.AnalysisModule;
import org.elasticsearch.plugins.AbstractPlugin;

import java.util.Collection;

/**
*
*/
public class WordEndingPlugin extends AbstractPlugin {

    @Override
    public String name() {
        return "wordending";
    }

    @Override
    public String description() {
        return "Add a delimiter at the end of each word, to ease build of autocompletion.";
    }

    public void onModule(AnalysisModule module) {
        module.addProcessor(new WordEndingBinderProcessor());
    }
}