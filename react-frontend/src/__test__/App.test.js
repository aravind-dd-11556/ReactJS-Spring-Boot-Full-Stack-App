javascript
import React from 'react';
import { render, screen } from '@testing-library/react';
import App from '../App';

test('renders learn react link', () => {
  render(<App />);
  const linkElement = screen.getByText(/learn react/i);
  expect(linkElement).toBeInTheDocument();
});

// Additional test to check if the component renders without crashing
test('renders App component without crashing', () => {
  const { container } = render(<App />);
  expect(container).toBeTruthy();
});