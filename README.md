## 🏗️ Implementation Details

### **1. Offline-First Pattern Implementation**
The app implements a repository pattern where the UI observes Room Database via Flow. On app launch, cached data displays immediately while Retrofit fetches fresh data in the background. Successful API responses update Room using `OnConflictStrategy.REPLACE`, triggering automatic UI updates through Flow. If the API fails, users still see cached data without any interruption.

### **2. Search Functionality Implementation**
Search is implemented using a custom SQL query in the UserDao (`@Query("SELECT * FROM users WHERE name LIKE '%' || :query || '%' OR email LIKE '%' || :query || '%'")`). The SearchView filters data directly from Room Database using Flow, ensuring search works offline and provides instant results without additional API calls.

## ✅ Features

- ✅ Fetches 10 users from JSONPlaceholder API
- ✅ Stores data locally using Room Database
- ✅ Works completely offline after first launch
- ✅ Real-time search by name or email
- ✅ Material Design UI with card-based layout
- ✅ Automatic data sync when online
- ✅ Single source of truth architecture

## 🧪 Testing Offline Mode

1. Open the app with internet connection
2. Wait for data to load
3. Enable Airplane Mode
4. Close and reopen the app
5. **Data is still visible!** ✅