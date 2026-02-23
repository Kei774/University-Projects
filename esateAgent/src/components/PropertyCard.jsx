import { Link } from "react-router-dom";

function PropertyCard({ property }) {
  return (
    <Link
      to={`/property/${property.id}`}
      className="property-link"
    >
      <div className="property-card">
        <img
          src={`/${property.picture}`}
          alt={property.type}
        />

        <h4>{property.type}</h4>

        <p><strong>Location:</strong> {property.location}</p>
        <p><strong>Bedrooms:</strong> {property.bedrooms}</p>
        <p><strong>Price:</strong> £{property.price}</p>
      </div>
    </Link>
  );
}

export default PropertyCard;