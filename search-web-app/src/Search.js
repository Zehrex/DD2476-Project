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
  searchKey: "search-akac6xdkk733fqhwpwpn8ioo",
  engineName: "github-search",
  endpointBase: "http://localhost:3002"
});

// Step #3: Configuration options
const configurationOptions = {
  apiConnector: connector,
  autocompleteQuery: {
    suggestions: {
      types: {
        documents: {
          // Which fields to search for suggestions.
          fields: ["method_name"]
        }
      },
      // How many suggestions appear.
      size: 5
    }
  },
  searchQuery: {
    search_fields: {
      // 1. Search by method name and snippet
      method_name: {},
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
      params: {
        snippet: {
          size: 150,
          fallback: true
        }
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
        snippet: {
          size: 150,
          fallback: true
        }
      },
      stars: {
        raw: {}
      },
      class: {
        snippet: {
          size: 150,
          fallback: true
        }
      },
      url: {
        raw: {}
      },
      snippet: {
        snippet: {
          fallback: true
        }
      }
    },
    // 3. Facets we'll use to build filters later.
    facets: {
      return_type: { type: "value", size: 20 },
      throws: { type: "value", size: 20 },
      stars: {
        type: "range",
        ranges: [
          { from: 0, to: 25, name: "Obscure" },
          { from: 25, to: 50, name: "Reputable" },
          { from: 50, to: 75, name: "Pretty Popular" },
          { from: 100, name: "Popular" }
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
              <Facet field="throws" label="Throws" filterType="any" isFilterable={true}/>
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
