import PropertyCard from "./PropertyCard";

function ResultsList({ properties }) {
  if (properties.length === 0) {
    return <p>No properties match your search criteria.</p>;
  }

  return (
    <>
      <h3>Results ({properties.length})</h3>

      <div className="results-grid">
        {properties.map((property) => (
          <PropertyCard key={property.id} property={property} />
        ))}
      </div>
    </>
  );
}

export default ResultsList;

