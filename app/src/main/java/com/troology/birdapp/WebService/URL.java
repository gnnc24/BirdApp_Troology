package com.troology.birdapp.WebService;

public interface URL {

    String baseUrl = "http://bird.theme-nulled.in/api/";

    interface RequestUrl {

        String UserLoginApi = baseUrl+"submit_user_record";
        String PostCardPostApi = baseUrl+"createpostcard";
        String Postcard_ThemeApi = baseUrl+"get_postcard_theme";
        String GetAddTextUrl = baseUrl+"text";
        String GetStatesUrl = "http://fairelamour.in/airportvaletoflax/api/"+"get_states";
        String CorporateRateUrl = baseUrl+"add_corporate_rate";
        String SpecialEventUrl = baseUrl+"add_special_event";
        String ContactUsUrl = baseUrl+"add_contact_us";
        String TestiMonialsUrl = baseUrl+"add_testimonials";
        String EmployeeLoginUrl = baseUrl+"do_sign_in";
        String UpdateTaskUrl = baseUrl+"update_task";

    }

}
