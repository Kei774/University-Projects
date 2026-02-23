import { render, screen } from "@testing-library/react";
import { BrowserRouter } from "react-router-dom";
import PropertyCard from "./PropertyCard";

const mockProperty = {
  id: "prop1",
  type: "House",
  location: "Test Location",
  price: 500000,
  bedrooms: 3,
  picture: "images/test.jpg",
};

test("renders property details correctly", () => {
  render(
    <BrowserRouter>
      <PropertyCard property={mockProperty} />
    </BrowserRouter>
  );

  expect(screen.getByText("House")).toBeInTheDocument();
  expect(screen.getByText(/Test Location/i)).toBeInTheDocument();
  expect(screen.getByText(/500000/i)).toBeInTheDocument();
});
