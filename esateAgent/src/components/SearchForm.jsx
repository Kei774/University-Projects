function SearchForm({ criteria, setCriteria }) {
  return (
    <form>
      <div>
        <label>Property Type</label>
        <select
          value={criteria.type}
          onChange={(e) =>
            setCriteria({ ...criteria, type: e.target.value })
          }
        >
          <option value="">Any</option>
          <option value="House">House</option>
          <option value="Flat">Flat</option>
        </select>
      </div>

      <div>
        <label>Min Price</label>
        <input
          type="number"
          value={criteria.minPrice}
          onChange={(e) =>
            setCriteria({ ...criteria, minPrice: e.target.value })
          }
        />
      </div>

      <div>
        <label>Max Price</label>
        <input
          type="number"
          value={criteria.maxPrice}
          onChange={(e) =>
            setCriteria({ ...criteria, maxPrice: e.target.value })
          }
        />
      </div>

      <div>
        <label>Min Bedrooms</label>
        <input
          type="number"
          value={criteria.minBeds}
          onChange={(e) =>
            setCriteria({ ...criteria, minBeds: e.target.value })
          }
        />
      </div>

      <div>
        <label>Max Bedrooms</label>
        <input
          type="number"
          value={criteria.maxBeds}
          onChange={(e) =>
            setCriteria({ ...criteria, maxBeds: e.target.value })
          }
        />
      </div>

      <div>
        <label>Postcode Area</label>
        <input
          type="text"
          value={criteria.postcode}
          onChange={(e) =>
            setCriteria({
              ...criteria,
              postcode: e.target.value.toUpperCase(),
            })
          }
        />
      </div>
    </form>
  );
}

export default SearchForm;
