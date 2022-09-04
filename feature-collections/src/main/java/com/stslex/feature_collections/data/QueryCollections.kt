package com.stslex.feature_collections.data

sealed class QueryCollections {
    class UserCollections(val query: String) : QueryCollections()
    object AllCollections : QueryCollections()
    object EmptyQuery : QueryCollections()
}
