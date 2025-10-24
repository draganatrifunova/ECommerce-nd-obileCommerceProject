import instance from "../custom-axios/axios"


const userRepository = {
    add: async (data) => {
        return await instance.post("/users/register", data);
    },

    edit: async (id, data) => {
        return await instance.put(`/users/update/${id}`, data);
    },

    delete: async (id) => {
        return await instance.delete(`/users/delete/${id}`);
    },


    findAll: async () => {
        return await instance.get("/users")
    },

    findById: async (id) => {
        return await instance.get(`/users/${id}`)
    },

    login: async (data) => {
        return await instance.post(`/users/login`, data)
    }
};

export default userRepository;