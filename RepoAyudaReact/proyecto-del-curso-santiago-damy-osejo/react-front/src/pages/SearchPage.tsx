export default function SearchPage() {
  return (
    <div>
      <h1>Search Products</h1>
      <input type="text" placeholder="Search..." />
      <button>Search</button>

      <div style={{ marginTop: '20px' }}>
        <h2>Results:</h2>
        <ul>
          <li>Product 1</li>
          <li>Product 2</li>
          <li>Product 3</li>
        </ul>
      </div>
    </div>
  );
}
