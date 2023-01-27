import axios from "axios";

// const users_api_base_irl = "http://localhost:8084/api/profile/employees";
// const users_api_base_irl = "http://localhost:8080/test_react/users";
const users_api_base_irl = "http://localhost:8080/Lost_documents/users";

class UserService {
  getUsers() {
    return axios.get(users_api_base_irl);
  }
}

export default new UserService();
