package com.tridya.foodrecipeblog.utils

import com.tridya.foodrecipeblog.database.tables.Notifications
import com.tridya.foodrecipeblog.models.IngredientModel
import com.tridya.foodrecipeblog.models.NotificationModel
import com.tridya.foodrecipeblog.models.ProcedureModel
import com.tridya.foodrecipeblog.models.ReviewModel
import com.tridya.foodrecipeblog.models.UserProfile

object StaticData {

    val listTimeFilter = listOf(
        "All",
        "Newest",
        "Oldest",
        "Popularity"
    )

    val listRateFilter = listOf(
        "5",
        "4",
        "3",
        "2",
        "1"
    )

    val listCategoryFilter = listOf(
        "All",
        "Dinner",
        "Lunch",
        "Fruit",
        "Rice",
        "Vegetables",
        "Vegan",
        "Breakfast",
        "Cake",
        "Fancy",
        "Soup",
        "Chicken",
        "Pizza",
        "Juice"
    )

    val tabsList = listOf(
        "Ingredient",
        "Procedure"
    )

    val notificationTitle = listOf(
        "All",
        "Read",
        "Unread"
    )
    val profileTitle = listOf(
        "Recipe",
        "Videos",
        "Tag"
    )
    val dummyNotification = Notifications(
        0,
        "NewRecipes",
        "New Recipes Are Added check it.",
        System.currentTimeMillis(),
        true
    )
    val listOfNotifications = listOf<NotificationModel>(
        NotificationModel(
            "New Recipe Alert!",
            "Lorem Ipsum tempor incididunt ut labore et dolore,in voluptate velit esse cillum",
            System.currentTimeMillis(),
            ""
        ),
        NotificationModel(
            "New Recipe Alert!",
            "Lorem Ipsum tempor incididunt ut labore et dolore,in voluptate velit esse cillum",
            System.currentTimeMillis(),
            ""
        ),
        NotificationModel(
            "New Recipe Alert!",
            "Lorem Ipsum tempor incididunt ut labore et dolore,in voluptate velit esse cillum",
            System.currentTimeMillis(),
            ""
        ),
        NotificationModel(
            "New Recipe Alert!",
            "Lorem Ipsum tempor incididunt ut labore et dolore,in voluptate velit esse cillum",
            System.currentTimeMillis(),
            ""
        )
    )

    /*fun getListOfNotification(): MutableList<NotificationModel> {
        for (i in 1..5) {
            notifications.add(
                NotificationModel(
                    "New Recipe Alert!",
                    "Lorem Ipsum tempor incididunt ut labore et dolore,in voluptate velit esse cillum",
                    System.currentTimeMillis(),
                    ""
                )
            )
        }
        return notifications
    }
*/
    val listOfIngredients = listOf<IngredientModel>(
        IngredientModel(
            itemName = "Tomatos",
            itemImage = "https://s3-alpha-sig.figma.com/img/3c30/4e61/3dd3e1dd1f7360a6ef1c04399b0609ab?Expires=1707091200&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=oGFdKDsyOISsa6kuoY~doR2jxkKIMLNKYOplNMxZtFi-ulcTtrVgaQaKa654CgSdseMUZ560PIN8HCGuK~DPxrmW~LkDsitpChB8nOrocnSlfsIr0cle~XswhvZaabF3dAGF5huDF5Kq8cM9E~ZDW7yU~2f450qKh13L0~gmN9FtLiP3XpeKWDXn8nLgvuKp5lRjIMUWyPTuf6FglKOSTyXDTIzcYVo2mtyreabWNLYFN1S~6L8eUD68cf0sPoRCGeENymlk-CTJKZc464YgYbKeQs29pcZy9nXn0gIhnY8pyTVs7Y2V9Tb8HdXMEyTSW62TG9ZNoOv-5ZeqTOXqqg__",
            itemWeight = 1000.0
        ),
        IngredientModel(
            itemName = "Cabbage",
            itemImage = "https://s3-alpha-sig.figma.com/img/c4b7/357b/e814ea5a6e1ead7b4cd7e47338d9823d?Expires=1707091200&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=YYb~9IY8Jnkx5aWKo9h2IDdAjI0mdb6PH3AecvRj6jfnuzXqb7xeBfS436NoGMBNvrDWsM0K05Hgk9m3YQIsC7QfP3YMGrUnZkj2xjiSeCmH3bcTiXHAySmN4wG2~J2mU0eJ8TZznIbPls2w8IckseqgPXFTMK235Gz5Jfsfts9KJDD3v~It71QyKEnCCnHY6VnYNWEtxS~sjafQL5oFP2jqDQtQ~dhi92JBpgdZb6abzG3dr96ai76ZDoYMiD8PaHU4Q1RxTrb~oYenwF~sBisyMWxkRNz1SR97N4~~luBalOvKJU4~IMTMtfPwLW91-nottraF8kfbrd8KPxjpAg__",
            itemWeight = 200.0
        ),
        IngredientModel(
            itemName = "Taco",
            itemImage = "https://s3-alpha-sig.figma.com/img/ac60/0470/bed72926848499c366186da9346e0e6e?Expires=1707091200&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=ZCbGAqybCqeOSgrFtJ8pkOJBvIf35RgP3KDweuKEgSIoYZydpNckDFLuSdI6natspUrIsLhbc-U2Pg71WL71WosX0BsnbgjfERttq5JfsPfSO6mj6BenLqGpFY5SOLnSVXGwQ1TGxk~-tGmybcaRMDnxI4e~40EpBtmP-7YQQ-EVVII4wKZS9n2oVfmeG2CIQqNFa0arx0IAq0eJ00Clo1Ao3AEtM6KafLYIzrW99pTRsfHiGKyXORTTeF1Nl8Obel3TMO7jzCo3YuN3LLXLbEZ6MKmwkRIiYy82vc3m2YwN1Z~Bc06Nbt2FJ0allGAIoORQO0ica6RnRIJ2~cG7cg__",
            itemWeight = 300.0
        ),
        IngredientModel(
            itemName = "Slice Bread",
            itemImage = "https://s3-alpha-sig.figma.com/img/61af/36f6/6a515fa814b8372e02f1ae569cff3ebd?Expires=1707091200&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=Mtn926vUUBys-tNQtImTwrwftxlwl6p8-NlhP1Tk33FhBcI-Sc4~E2OMOfhD26dV6mmgvFopkzrrlSdcOHuzxQzi~hxzID9BJpSyBVtwI-6-G-bBHBlalLq6wBD4lPZCjyoOv4isLzJWtzGhSxBMU5hdMjmUgXQ9KIORq~3IEK2bVjbR6ysHmuLqnedP7MNjOP682COpxVRg02FCcaZRjia7XNjwuO4xowgQFE2z5s7ZSXSjV2pyRfm~aPDB6tAH~JEB3WxCxa--bvZrdifR3boDINDlVc-ZN76GDj1eYZNkestchbM7FFS~hEzK6UrAbWnCBi-iQK7TZUuz8MHiSg__",
            itemWeight = 100.0
        ),
        IngredientModel(
            itemName = "Tomatos",
            itemImage = "https://s3-alpha-sig.figma.com/img/3c30/4e61/3dd3e1dd1f7360a6ef1c04399b0609ab?Expires=1707091200&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=oGFdKDsyOISsa6kuoY~doR2jxkKIMLNKYOplNMxZtFi-ulcTtrVgaQaKa654CgSdseMUZ560PIN8HCGuK~DPxrmW~LkDsitpChB8nOrocnSlfsIr0cle~XswhvZaabF3dAGF5huDF5Kq8cM9E~ZDW7yU~2f450qKh13L0~gmN9FtLiP3XpeKWDXn8nLgvuKp5lRjIMUWyPTuf6FglKOSTyXDTIzcYVo2mtyreabWNLYFN1S~6L8eUD68cf0sPoRCGeENymlk-CTJKZc464YgYbKeQs29pcZy9nXn0gIhnY8pyTVs7Y2V9Tb8HdXMEyTSW62TG9ZNoOv-5ZeqTOXqqg__",
            itemWeight = 500.0
        ),
        IngredientModel(
            itemName = "Cabbage",
            itemImage = "https://s3-alpha-sig.figma.com/img/c4b7/357b/e814ea5a6e1ead7b4cd7e47338d9823d?Expires=1707091200&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=YYb~9IY8Jnkx5aWKo9h2IDdAjI0mdb6PH3AecvRj6jfnuzXqb7xeBfS436NoGMBNvrDWsM0K05Hgk9m3YQIsC7QfP3YMGrUnZkj2xjiSeCmH3bcTiXHAySmN4wG2~J2mU0eJ8TZznIbPls2w8IckseqgPXFTMK235Gz5Jfsfts9KJDD3v~It71QyKEnCCnHY6VnYNWEtxS~sjafQL5oFP2jqDQtQ~dhi92JBpgdZb6abzG3dr96ai76ZDoYMiD8PaHU4Q1RxTrb~oYenwF~sBisyMWxkRNz1SR97N4~~luBalOvKJU4~IMTMtfPwLW91-nottraF8kfbrd8KPxjpAg__",
            itemWeight = 200.0
        ),
        IngredientModel(
            itemName = "Taco",
            itemImage = "https://s3-alpha-sig.figma.com/img/ac60/0470/bed72926848499c366186da9346e0e6e?Expires=1707091200&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=ZCbGAqybCqeOSgrFtJ8pkOJBvIf35RgP3KDweuKEgSIoYZydpNckDFLuSdI6natspUrIsLhbc-U2Pg71WL71WosX0BsnbgjfERttq5JfsPfSO6mj6BenLqGpFY5SOLnSVXGwQ1TGxk~-tGmybcaRMDnxI4e~40EpBtmP-7YQQ-EVVII4wKZS9n2oVfmeG2CIQqNFa0arx0IAq0eJ00Clo1Ao3AEtM6KafLYIzrW99pTRsfHiGKyXORTTeF1Nl8Obel3TMO7jzCo3YuN3LLXLbEZ6MKmwkRIiYy82vc3m2YwN1Z~Bc06Nbt2FJ0allGAIoORQO0ica6RnRIJ2~cG7cg__",
            itemWeight = 300.0
        ),
        IngredientModel(
            itemName = "Slice Bread",
            itemImage = "https://s3-alpha-sig.figma.com/img/61af/36f6/6a515fa814b8372e02f1ae569cff3ebd?Expires=1707091200&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=Mtn926vUUBys-tNQtImTwrwftxlwl6p8-NlhP1Tk33FhBcI-Sc4~E2OMOfhD26dV6mmgvFopkzrrlSdcOHuzxQzi~hxzID9BJpSyBVtwI-6-G-bBHBlalLq6wBD4lPZCjyoOv4isLzJWtzGhSxBMU5hdMjmUgXQ9KIORq~3IEK2bVjbR6ysHmuLqnedP7MNjOP682COpxVRg02FCcaZRjia7XNjwuO4xowgQFE2z5s7ZSXSjV2pyRfm~aPDB6tAH~JEB3WxCxa--bvZrdifR3boDINDlVc-ZN76GDj1eYZNkestchbM7FFS~hEzK6UrAbWnCBi-iQK7TZUuz8MHiSg__",
            itemWeight = 100.0
        ),
    )

    val listOfProcedureSteps = listOf<ProcedureModel>(
        ProcedureModel(
            1,
            "Lorem Ipsum tempor incididunt ut labore et dolore,in voluptate velit esse cillum dolore eu fugiat nulla pariatur?"
        ),
        ProcedureModel(
            2,
            "Lorem Ipsum tempor incididunt ut labore et dolore,in voluptate velit esse cillum dolore eu fugiat nulla pariatur?\n" +
                    "Tempor incididunt ut labore et dolore,in voluptate velit esse cillum dolore eu fugiat nulla pariatur?"
        ),
        ProcedureModel(
            3,
            "Lorem Ipsum tempor incididunt ut labore et dolore,in voluptate velit esse cillum dolore eu fugiat nulla pariatur?"
        ),
        ProcedureModel(
            4,
            "Lorem Ipsum tempor incididunt ut labore et dolore,in voluptate velit esse cillum dolore eu fugiat nulla pariatur?\n" +
                    "Tempor incididunt ut labore et dolore,in voluptate velit esse cillum dolore eu fugiat nulla pariatur?"
        ),
        ProcedureModel(
            5,
            "Lorem Ipsum tempor incididunt ut labore et dolore,in voluptate velit esse cillum dolore eu fugiat nulla pariatur?"
        ),
        ProcedureModel(
            6,
            "Lorem Ipsum tempor incididunt ut labore et dolore,in voluptate velit esse cillum dolore eu fugiat nulla pariatur?"
        ),

        )


    val listCountries = listOf(
        "All",
        "Indian",
        "Italian",
        "Asian",
        "Chinese",
        "Spanish",
        "French",
        "Maxican",
        "Korean",
        "Thai",
        "Cousine",
        "Greek"
    )

    val userProfile = UserProfile(
        "Afuwape Abiodun", "https://images.unsplash.com/photo-1494790108377-be9c29b29330?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D", 4, 200, 100, "Chef", "Private Chef\n" +
                "Passionate about food and life \uD83E\uDD58\uD83C\uDF72\uD83C\uDF5D\uD83C\uDF71\n" +
                "More..."
    )
    val listOfReviews = listOf<ReviewModel>(
        ReviewModel(
            0,
            "Bella Throne",
            "https://unsplash.com/photos/closeup-photography-of-woman-smiling-mEZ3PoFGs_k",
            "12/06/2023 19:35",
            "Lorem Ipsum tempor incididunt ut labore et dolore,inise voluptate velit esse cillum",
            9,
            2
        ),
        ReviewModel(
            1,
            "Christopher Oshana",
            "https://unsplash.com/photos/man-taking-selfie-tidSLv-UaNs",
            "12/06/2023 20:00",
            "Lorem Ipsum tempor incididunt ut labore et dolore,inise voluptate.",
            7,
            1
        ),
        ReviewModel(
            2,
            "Kyle Austin",
            "https://unsplash.com/photos/man-in-black-button-up-shirt-ZHvM3XIOHoE",
            "12/06/2023 20:00",
            "Lorem Ipsum tempor incididunt ut labore et dolore,inise voluptate.",
            7,
            1
        ),
        ReviewModel(
            3,
            "Jeniffern Wilson",
            "https://unsplash.com/photos/woman-in-black-and-white-checkered-hoodie-OFAbs8e8Zic",
            "12/06/2023 19:35",
            "Lorem Ipsum tempor incididunt ut labore et dolore,inise voluptate velit esse cillum",
            9,
            2
        ),
        ReviewModel(
            4,
            "Kate Johnson",
            "https://unsplash.com/photos/woman-in-white-off-shoulder-dress-uR51HXLO7G0",
            "12/06/2023 20:00",
            "Lorem Ipsum tempor incididunt ut labore et dolore,inise voluptate.",
            7,
            1
        ),
        ReviewModel(
            5,
            "Kyle Austin",
            "https://unsplash.com/photos/man-in-black-button-up-shirt-ZHvM3XIOHoE",
            "12/06/2023 20:00",
            "Lorem Ipsum tempor incididunt ut labore et dolore,inise voluptate.",
            7,
            1
        ),
    )
}

