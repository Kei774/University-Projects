import { useState } from "react";
import SearchForm from "../components/SearchForm";
import ResultsList from "../components/ResultsList";

function SearchPage({ properties, favourites, setFavourites }) {

  const [criteria, setCriteria] = useState({
    type: "",
    minPrice: "",
    maxPrice: "",
    minBeds: "",
    maxBeds: "",
    postcode: "",
  });

  const [showFavouritesOnly, setShowFavouritesOnly] = useState(false);

  const filteredProperties = properties.filter((property) => {
    if (showFavouritesOnly && !favourites.includes(property.id)) {
      return false;
    }

    if (criteria.type && property.type !== criteria.type) return false;

    if (criteria.minPrice && property.price < Number(criteria.minPrice))
      return false;

    if (criteria.maxPrice && property.price > Number(criteria.maxPrice))
      return false;

    if (criteria.minBeds && property.bedrooms < Number(criteria.minBeds))
      return false;

    if (criteria.maxBeds && property.bedrooms > Number(criteria.maxBeds))
      return false;

    if (
      criteria.postcode &&
      !property.location.toUpperCase().includes(criteria.postcode)
    )
      return false;

    return true;
  });


  return (
    <div className="search-page">
      <h2>Search Properties</h2>

      <div className="filter-toggle">
        <button
          className={!showFavouritesOnly ? "active" : ""}
          onClick={() => setShowFavouritesOnly(false)}>
          All Properties
        </button>

        <button
          className={showFavouritesOnly ? "active" : ""}
          onClick={() => setShowFavouritesOnly(true)}>
          Favourites
        </button>

        {favourites.length > 0 && (
          <button
            className="clear-favs"
            onClick={() => setFavourites([])}>
            Clear favourites
          </button>
        )}
      </div>

      <SearchForm criteria={criteria} setCriteria={setCriteria} />

      <ResultsList properties={filteredProperties} />
    </div>
  );

}

export default SearchPage;
