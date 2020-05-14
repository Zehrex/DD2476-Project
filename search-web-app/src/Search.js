// Step #1, import statements
import React from "react";
import AppSearchAPIConnector from "@elastic/search-ui-app-search-connector";
import {
  PagingInfo,
  ResultsPerPage,
  Paging,
  Facet,
  SearchProvider,
  Results,
  SearchBox,
  Sorting
} from "@elastic/react-search-ui";
import { Layout, SingleLinksFacet } from "@elastic/react-search-ui-views";
import "@elastic/react-search-ui-views/lib/styles/styles.css";

// Step #2, The connector
const connector = new AppSearchAPIConnector({
  searchKey: "search-se19itwcs6g3uduqu355h5to",
  engineName: "github-search-2",
  endpointBase: "https://58c1c61a563a4706b431752249a88b66.app-search.us-central1.gcp.cloud.es.io"
});

// Step #3: Configuration options
const configurationOptions = {
  apiConnector: connector,
  autocompleteQuery: {
    suggestions: {
      types: {
        documents: {
          // Which fields to search for suggestions.
          fields: ["method_name", "class_name"]
        }
      },
      // How many suggestions appear.
      size: 5
    }
  },
  searchQuery: {
    search_fields: {
      // 1. Search by method name, class name and snippet
      method_name: {},
      class_name: {},
      snippet: {}
    },
    result_fields: {
      method_name: {
        // A snippet means that matching search terms will be highlighted via <em> tags.
        snippet: {
          size: 100, // Limit the snippet to 75 characters.
          fallback: true // Fallback to a "raw" result.
        }
      },
      class_name: {
        // A snippet means that matching search terms will be highlighted via <em> tags.
        snippet: {
          size: 100, // Limit the snippet to 75 characters.
          fallback: true // Fallback to a "raw" result.
        }
      },
      params: {
        raw: {}
      },
      throws: {
        snippet: {
          size: 150,
          fallback: true
        }
      },
      modifiers: {
        snippet: {
          size: 150,
          fallback: true
        }
      },
      return_type: {
        raw: {}
      },
      stars: {
        raw: {}
      },
      class_props: {
        raw: {}
      },
      url: {
        raw: {}
      },
      snippet: {
        raw: {}
      }
    },
    // 3. Facets we'll use to build filters later.
    facets: {
      return_type: { type: "value", size: 20 },
      modifiers: { type: "value", size: 20 },
      throws: { type: "value", size: 20 },
      params: { type: "value", size: 20 },
      stars: {
        type: "range",
        ranges: [
          { from: 0, to: 25, name: "Obscure (0-25)" },
          { from: 25, to: 50, name: "Reputable (25-50)" },
          { from: 50, to: 75, name: "Pretty Popular (50-75)" },
          { from: 100, name: "Popular (100+)" }
        ]
      },
    }
  }
};

// Step #4, SearchProvider: The finishing touches
export default function Search() {
  return (
    <SearchProvider config={configurationOptions}>
      <div className="App">
        <Layout
          header={<SearchBox autocompleteSuggestions={true} />}
          bodyContent={<Results titleField="method_name" urlField="url" />}
          sideContent={
            <div>
              <Sorting
                label={"Sort by"}
                sortOptions={[
                  {
                    name: "Relevance",
                    value: "",
                    direction: ""
                  },
                  {
                    name: "Name",
                    value: "method_name",
                    direction: "asc"
                  },
                  {
                    name: "Repository Stars",
                    value: "stars",
                    direction: "desc"
                  }
                ]}
              />
              <Facet field="return_type" label="Return Type" filterType="any" isFilterable={true}/>
              <Facet field="modifiers" label="Modifiers" filterType="any" isFilterable={true}/>
              <Facet field="throws" label="Throws" filterType="any" isFilterable={true}/>
              <Facet field="params" label="Parameters" filterType="any" isFilterable={true}/>
              <Facet field="stars" label="Number of Repo Stars" view={SingleLinksFacet} />
            </div>
          }
          bodyHeader={
            <>
              <PagingInfo />
              <ResultsPerPage />
            </>
          }
          bodyFooter={<Paging />}
        />
      </div>
    </SearchProvider>
  );
}
