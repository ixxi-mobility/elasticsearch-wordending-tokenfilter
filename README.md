# elasticsearch-wordending-tokenfilter

Add a char to mark the end of each token. Useful in autocomplete search, used in
conjunction with edge-ngrams.

The tokenfilter has two modes: one for indexing, one for searching, the only
difference is that in searching mode the wordending char is not set on the
**last** word because we guess it's the beginning of the word being typed.

## Install

First, make sure `ES_HOME` is set. If not, export it, something like this:
```
export ES_HOME="/usr/share/elasticsearch"
```

Then, run the following commands after having cloned the repo:
```
make package
make install
```

## Sample usage
```
{
    "analysis": {
        "analyzer": {
            "index": {
                "tokenizer": "standard",
                "filter": ["lowercase", "asciifolding", "wordending", "ngram"],
            },
            "search": {
                "tokenizer": "standard",
                "filter": ["lowercase", "asciifolding", "wordendingautocomplete"],
            },
        },
        "filter": {
            "ngram": {
                "type": "edgeNGram",
                "min_gram": 2,
                "max_gram": 15
            },
            "wordending": {
                "type": 'wordending',
                "mode": "default"
            },
            "wordendingautocomplete": {
                "type": 'wordending',
                "mode": "autocomplete"
            },
        },
    }
}

```