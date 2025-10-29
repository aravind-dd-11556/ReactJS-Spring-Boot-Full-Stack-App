javascript
import React, { useEffect, useState } from 'react';
import { fetchHobbies } from '../api/hobbyApi'; // Assuming there's a fetch function

const HobbyList = () => {
  const [hobbies, setHobbies] = useState([]);
  const [isLoading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const loadHobbies = async () => {
      try {
        const data = await fetchHobbies();
        setHobbies(data);
      } catch (err) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    };

    loadHobbies();
  }, []);

  if (isLoading) return <div>Loading...</div>;
  if (error) return <div>Error: {error}</div>;

  return (
    <ul>
      {hobbies.map((hobby) => (
        <li key={hobby.id}>{hobby.name}</li>
      ))}
    </ul>
  );
};

export default HobbyList;