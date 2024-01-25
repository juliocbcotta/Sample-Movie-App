## First things first

... This README is in WIP...

I am not the owner of the truth, this repo code, and README.MD texts are based on my knowledge and views, feel free to
disagree, open issues, or just say that I am wrong.

The internet is a place full of very critical people who think that understand how software should be developed,
I am no different, I think I know, and to show it, I am playing around with this project.
I would love to receive some constructive feedback about this project.

My goal is just to learn and maybe share some knowledge/views while also being a bit cranky.

## What

This project is meant to try to show a pluggable architecture in an Android app, and **you will hate it**!

Lots of interfaces are created with that goal in mind, yeah, that is right, lots of interfaces, interfaces everywhere,
for everything!
And I mean it, even for your model classes!

## Why

Well, if you just develop software because you want to deliver something for today, and don't need to worry about the
next 6 months or 2 years, just move on and close this browser tab. Don't waste your time reading me.

If you think that software can be developed in a way that would make it easier to keep working for years,
even though parts of it need to be changed from time to time and also while those changes happen, stick around.

One thing that you may be sure of is, technologies will change with time, and making your code "pluggable" will allow
adoption of those new shiny techs with relative ease.

Four years ago we were sure that RxJava was here to stay, three years ago LiveData was here to stay, and now it is
coroutine Flows...

> "Ahh, but that is just one exception, it won't change again..."

- findViewById -> Android Annotations -> Android Extensions -> Data Binding -> ViewBinding -> Compose
- GSON -> Moshi -> Kotlin Serialization<sup>(1)</sup>
- URLConnection -> Volley -> OkHttp -> Ktor<sup>(1)</sup>
- Direct access to the Http client -> Retrofit -> KtorFit<sup>(1)</sup>
- God Activity -> God Fragments -> Presenters -> ViewModels
- ContentValues -> DbFlow -> Room/ Sqldelight<sup>(1)</sup>
- Manual DI -> Dagger2 -> Dagger Android -> Hilt -> Koin<sup>(1)(2)</sup>

(1) Multiplatform libraries as a differential over other options have been a cause for migrations

(2) I like pure Dagger better, you just have more freedom.

Have I made my point clear? All those libraries are implementation details inside your app.
If you plan to make any migration of those or others, it is wise to limit your changes to isolated parts of your app and
possibly keep the old and new versions working at the same time.
To limit the risk of one implementation impacting another, you should keep all implementations while you ensure the new
implementation does not bring regressions.

## Hexagonal Arch like...

Back to this project, I am implementing this project using some ideas borrowed from Hexagonal Arch,
but I didn't like naming everything ports and adapters, so I kept the names that I am used to. If I understood the basic idea of Hexagonal Arch, each port is an interface, and each
adapter is an implementation to that interface, so you can swap implementations as needed.

If you dig around this project code, you will notice that the feature code is split into multiple modules, most of them easily
recognizable
by their names, but there is this `abstraction` module that you may find a bit odd.

`abstraction` modules are meant to be the blueprint of your feature, so, only interfaces are allowed there, and most
feature modules
should depend on it.

> "But on the arch XYZ that means we are exposing too much to all layers!"

As I said before "blueprint for the feature", the emphasis here is on **feature**, with an `abstraction` you can replace any layer (or all layers) of your feature
with another implementation and it should just work.
Also, if you want, you could split the `abstraction` module into smaller modules by layer. I am too old and too tired for that.

In some other archs, you may relate `abstraction` with the `domain` layer, we can have a `domain` in this project,
but it will only host implementations for UseCases, the `domain` is stripped of its powers in favor of having a strong
isolation in between behavior definition and implementations.

You totally can have a pluggable arch without having an `abstraction` module, just make every communication in between layers (and sometimes inside the layer) an interface and you have it, but do you implement that?

Well, with this sample project, I am trying to enforce it.

Until now, what I noticed is that many times in Android development we forget about some basic SOLID principles...
Yeah

I know

I know

that SOLID

Do you remember what are they?

Do you know how to identify them or the breakage of them in an Android project?

What are the consequences of breaking those principles?

For instance,

You see a Response implementation in the UI layer, with some parsing annotations, like `@SerializedName` for GSON and `@Parcelize` for Parcelable for Android state restoration in the same class definition.
This means that this same model implementation is being used in multiple layers of the application with different responsibilities, more, you are exposing implementation details of one layer to another.
The UI doesn't need to know about how to parse this model as JSON, the data layer does not need (should not) know about
this model's capabilities of being parcelized, and no layer in between those two (data and UI) should know about any of
it.

> "Why? Why not?"

Well, I for sure don't think too much about the "Single Responsibility Principle", but I do think that something stinks because tomorrow you will migrate from Gson to Kotlin Serialization in the project, and at that moment you will have two options.

1. Go all in removing the Gson annotations and adding the new ones.
2. Keep both annotations in the same model.

In 1, you may break something in production and there is no way to switch back to the old implementation.
In 2, you may be able to switch between implementations but two sprints later when you decide to remove the old annotations you need to edit all those models again bringing the possibility of breaking something again.

If the models you pass around your layers were interfaces, they would be implementation details free (hopefully).

Your Response model would implement that model interface, your Parcelable model as well, each on its layer, and every layer in between would see only the interface.

With that we have a 3rd option for the migration above, you can keep the old models with GSON annotations in a package and the new models with Kotlin Serialization in another package.
That allows switching between them and also an easy removal when the moment comes.



... This readme is still in WIP, come back later...

## To run this project, you will need

- OMDB API KEY [here](https://www.omdbapi.com/apikey.aspx), place it in `api.properties`

```
apiKey=YOUR_API_KEY
```

- GitHub Personal Access Token, get it in [here](https://github.com/settings/tokens), place it on
  `github.properties`

````
github.user=YOUR_GITHUB_USER
github.token=YOUR_GITHUB_TOKEN
