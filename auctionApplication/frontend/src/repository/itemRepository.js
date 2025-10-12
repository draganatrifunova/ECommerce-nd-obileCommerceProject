import instance from "../custom-axios/axios";

const itemRepository = {
    findById: async (id) => {
        return await instance.get(`/items/${id}`);
    },


    add: async (formData) => {
        return await instance.post("/items/add", formData, {
            headers: {
                "Content-Type": "multipart/form-data"
            }
        });
    },

    getImage: async (id) => {
        return await instance.get(`/items/${id}/image`, {
            responseType: "blob"
        });
    },


    update: async (id, formData) => {
        return await instance.put(`/items/update/${id}`, formData, {
            headers: {
                "Content-Type": "multipart/form-data"
            }
        });
    },


    listAll: async () => {
        return await instance.get("/items");
    },


    delete: async (id) => {
        return await instance.delete(`/items/${id}`);
    }
};

export default itemRepository;
