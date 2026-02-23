import { render, screen } from "@testing-library/react";
import SearchPage from "./SearchPage";
import { BrowserRouter } from "react-router-dom";


const properties = [
  { id: "1", type: "House", price: 300000, bedrooms: 3, location: "BR5" },
  { id: "2", type: "Flat", price: 600000, bedrooms: 2, location: "BR6" },
];

test("shows all properties when no filters applied", () => {
    render(
    <BrowserRouter>
        <SearchPage
        properties={properties}
        favourites={[]}
        setFavourites={() => {}}
        />
    </BrowserRouter>
    );


  expect(screen.getAllByText(/£/).length).toBe(2);
});
