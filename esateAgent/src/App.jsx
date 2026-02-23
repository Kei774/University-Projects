import { Routes, Route } from "react-router-dom";
import { useState, useEffect } from "react";
import SearchPage from "./pages/SearchPage";
import PropertyPage from "./pages/PropertyPage";
import propertiesData from "./data/properties.json";

function App() {
  const [favourites, setFavourites] = useState(() => {
    const saved = localStorage.getItem("favourites");
    return saved ? JSON.parse(saved) : [];
  });

  useEffect(() => {
    localStorage.setItem("favourites", JSON.stringify(favourites));
  }, [favourites]);

  return (
    <div className="app">
      <h1>Estate Agent App</h1>

      <Routes>
        <Route
          path="/"
          element={
            <SearchPage
              properties={propertiesData.properties}
              favourites={favourites}
              setFavourites={setFavourites}
            />
          }
        />

        <Route
          path="/property/:id"
          element={
            <PropertyPage
              properties={propertiesData.properties}
              favourites={favourites}
              setFavourites={setFavourites}
            />
          }
        />
      </Routes>
    </div>
  );
}

export default App;