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

## Screenshots
<img width="1080" height="2400" alt="Screenshot_20251126_173011" src="https://github.com/user-attachments/assets/29b98b0a-ad2d-4458-b70d-e5c4047fead3" />
<img width="1080" height="2400" alt="Screenshot_20251126_173000" src="https://github.com/user-attachments/assets/6d48b83e-ecb2-4f1d-9afb-8a4303bea1f4" />
<img width="1080" height="2400" alt="Screenshot_20251126_172631" src="https://github.com/user-attachments/assets/17544fdd-7ea4-473b-a190-76a2939d0434" />
<img width="1080" height="2400" alt="Screenshot_20251126_172916" src="https://github.com/user-attachments/assets/d466d06e-4a90-4af4-a84e-374c6ff431b0" />
<img width="1080" height="2400" alt="Screenshot_20251126_172759" src="https://github.com/user-attachments/assets/adc61af6-c919-4613-a54d-48a9109d15b6" />
