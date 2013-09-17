package biz.ixxi.analysis.wordending;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.WhitespaceTokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.elasticsearch.common.lucene.Lucene;

import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import static org.hamcrest.Matchers.equalTo;


public class WordEndingFilterTest {

    @Test
    public void defaultModeTest() throws IOException {
        Analyzer analyzer = new Analyzer() {
            @Override
            protected TokenStreamComponents createComponents(String fieldName,
                                                             Reader reader) {
                Tokenizer t = new WhitespaceTokenizer(Lucene.VERSION, reader);
                return new TokenStreamComponents(t, new WordEndingFilter(t, "default"));
            }
        };

        final TokenStream test = analyzer.tokenStream("test", new StringReader("A simple phrase"));
        test.reset();
        CharTermAttribute termAttribute = test.addAttribute(CharTermAttribute.class);
        assertTrue(test.incrementToken());
        assertEquals(termAttribute.toString(), "A$");

        assertTrue(test.incrementToken());
        assertEquals(termAttribute.toString(), "simple$");

        assertTrue(test.incrementToken());
        assertEquals(termAttribute.toString(), "phrase$");

        assertFalse(test.incrementToken());
    }

    @Test
    public void defaultModeOneTokenTest() throws IOException {
        Analyzer analyzer = new Analyzer() {
            @Override
            protected TokenStreamComponents createComponents(String fieldName,
                                                             Reader reader) {
                Tokenizer t = new WhitespaceTokenizer(Lucene.VERSION, reader);
                return new TokenStreamComponents(t, new WordEndingFilter(t, "default"));
            }
        };

        final TokenStream test = analyzer.tokenStream("test", new StringReader("token"));
        test.reset();
        CharTermAttribute termAttribute = test.addAttribute(CharTermAttribute.class);
        assertTrue(test.incrementToken());
        assertEquals(termAttribute.toString(), "token$");

        assertFalse(test.incrementToken());
    }

    @Test
    public void autocompleteModeTest() throws IOException {
        Analyzer analyzer = new Analyzer() {
            @Override
            protected TokenStreamComponents createComponents(String fieldName,
                                                             Reader reader) {
                Tokenizer t = new WhitespaceTokenizer(Lucene.VERSION, reader);
                return new TokenStreamComponents(t, new WordEndingFilter(t, "autocomplete"));
            }
        };

        final TokenStream test = analyzer.tokenStream("test", new StringReader("A simple phrase"));
        test.reset();
        CharTermAttribute termAttribute = test.addAttribute(CharTermAttribute.class);
        assertTrue(test.incrementToken());
        assertEquals(termAttribute.toString(), "A$");

        assertTrue(test.incrementToken());
        assertEquals(termAttribute.toString(), "simple$");

        assertTrue(test.incrementToken());
        assertEquals(termAttribute.toString(), "phrase");

        assertFalse(test.incrementToken());
    }

    @Test
    public void autocompleteModeOneTokenTest() throws IOException {
        Analyzer analyzer = new Analyzer() {
            @Override
            protected TokenStreamComponents createComponents(String fieldName,
                                                             Reader reader) {
                Tokenizer t = new WhitespaceTokenizer(Lucene.VERSION, reader);
                return new TokenStreamComponents(t, new WordEndingFilter(t, "autocomplete"));
            }
        };

        final TokenStream test = analyzer.tokenStream("test", new StringReader("token"));
        test.reset();
        CharTermAttribute termAttribute = test.addAttribute(CharTermAttribute.class);

        assertTrue(test.incrementToken());
        assertEquals(termAttribute.toString(), "token");

        assertFalse(test.incrementToken());
    }
}
