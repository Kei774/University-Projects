import { useParams } from "react-router-dom";
import { useState } from "react";

function PropertyPage({ properties, favourites, setFavourites }) {
  const { id } = useParams();
  const [tab, setTab] = useState("description");

  const property = properties.find((p) => p.id === id);

  if (!property) {
    return <p>Property not found.</p>;
  }

  const isFavourite = favourites.includes(property.id);

  const toggleFavourite = () => {
    if (isFavourite) {
      setFavourites(favourites.filter((favId) => favId !== property.id));
    } else {
      setFavourites([...favourites, property.id]);
    }
  };

  const images = property.images || [];
  const [currentImage, setCurrentImage] = useState(0);

  const nextImage = () => {
    setCurrentImage((prev) =>
      prev === images.length - 1 ? 0 : prev + 1
    );
  };

  const prevImage = () => {
    setCurrentImage((prev) =>
      prev === 0 ? images.length - 1 : prev - 1
    );
  };
  if (!images.length) {
    return <p>No images available.</p>;
  }

  return (
    <div className="property-page">
      <div className="property-hero">
        <button className="img-arrow left" onClick={prevImage}>
          ‹
        </button>

        <img
          src={`/${images[currentImage]}`}
          alt={`${property.type} view`}
        />

        <button className="img-arrow right" onClick={nextImage}>
          ›
        </button>
        
      </div>

      <div className="property-info">
        <h2>{property.type}</h2>
        <p>{property.location}</p>

        <button className="fav-btn" onClick={toggleFavourite}>
          {isFavourite ? "Remove from favourites" : "Add to favourites"}
        </button>

        <div className="tabs">
          <button
            className={tab === "description" ? "active" : ""}
            onClick={() => setTab("description")}
          >
            Description
          </button>

          <button
            className={tab === "details" ? "active" : ""}
            onClick={() => setTab("details")}
          >
            Details
          </button>
          <button
            className={tab === "map" ? "active" : ""}
            onClick={() => setTab("map")}
          >
            Map
          </button>

          <button
            className={tab === "floorplan" ? "active" : ""}
            onClick={() => setTab("floorplan")}
          >
            Floorplan
          </button>

        </div>

        <div className="tab-content">
          {tab === "floorplan" && (
            <img
              src={`/images/${property.id}/floorplan.jpg`}
              alt="Property floor plan"
              className="floorplan-img"
            />
          )}
          {tab === "description" && <p>{property.description}</p>}

          {tab === "details" && (
            <ul>
              <li>Bedrooms: {property.bedrooms}</li>
              <li>Price: £{property.price}</li>
              <li>Tenure: {property.tenure}</li>
            </ul>
          )}

          {tab === "map" && (
            <iframe
              title="Property location"
              src={`https://www.google.com/maps?q=${encodeURIComponent(
                property.location
              )}&output=embed`}
              width="100%"
              height="300"
              style={{ border: 0, borderRadius: "16px" }}
              loading="lazy"
              referrerPolicy="no-referrer-when-downgrade"
            />
          )}
        </div>
      </div>
    </div>
  );

}

export default PropertyPage;
