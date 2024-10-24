# Briefly describe what ML framework does this handler integrate to MindsDB, and how?
NeuralForecast is a ML package for time series forecasting.
We have integrated the NHITS and AutoNHITS models from this package.
These are flexible, deep-learning models for time-series forecasting, based on https://arxiv.org/abs/2201.12886
Neuralforecast documentation is available at https://nixtla.github.io/neuralforecast/

Call this handler by
`USING ENGINE='neuralforecast'` - you can see a full example in https://github.com/mindsdb/mindsdb/pull/4615

# Why is this integration useful? What does the ideal predictive use case for this integration look like? When would you definitely not use this integration?
Neuralforecast uses fast and accurate deep learning methods.
These can make accurate forecasts, with little domain knowledge about the problem requires, as they will automatically assign appropriate weights to exogenous variables.
The ideal use case is forecasting complex time series, such as predicting high-frequency energy prices over the course of each day.

This handler will also be useful when data has a hierarchical structure (for example: you have a series for each region in a certain country).
Users can supply an optional argument to perform hierarchical reconciliation during forecasting with Nixtla's HierarchicalForecast package.
Reconciliation may improve forecast accuracy, by accounting for the hierarchical structure in the data.
You can learn more about this package at https://nixtla.github.io/hierarchicalforecast/

Do not use this integration for non time-series data.

Do not use this integration for short time-series, as they won't have enough data to prevent overfitting.
Instead, we recommend using the Statsforecast handler, whose classical methods are more suited to small datasets.

# Are models created with this integration fast and scalable, in general?
Model training can be slow, as the default implementation will search different network architectures and hyperparameters.
Once the model is trained, forecasting is very fast.

# What are the recommended system specifications for models created with this framework?
Models can be trained on either CPU or GPU.

# To what degree can users control the underlying framework by passing parameters via the USING syntax?
Users define the forecast horizon with the `horizon` parameter.

The training window is defined with the `window` parameter. Note that Neuralforecast will ignore this parameter if allowed to train for long enough, as it will automatically select the best training window as part of hyperparameter tuning.

Users can specify `train_time` as a USING arg (see example in PR). `train_time` $\in [0, 1]$. This defaults to 1, and lower values will reduce trainig time linearly by reducing the number of searches allowed for the best configuration by AutoNHITS.

Users can define `exogenous_vars` as a USING arg. These are complementary variables in the table that may improve forecast accuracy. Pass this as a list of strings e.g. `USING exogenous_vars=['var_1', 'var_2']`

Users can optionally define a hierarchy to the data, with the "hierarchy" arg.
The model will reconcile forecasts according to the hierarchy, which may improve their accuracy.
This is further explained in https://github.com/mindsdb/mindsdb/pull/5605

# Does this integration offer model explainability or insights via the DESCRIBE syntax?
Yes - see https://github.com/mindsdb/mindsdb/pull/5445 for an example of how to call the DESCRIBE method.

# Does this integration support fine-tuning pre-existing models (i.e. is the update() method implemented)? Are there any caveats?
Not implemented yet.

# Are there any other noteworthy aspects to this handler?
The default configuration will use AutoNHITs, which automatically searches for the best network architecture and hyperparameters.
However, this can take a long time.
If users specify a very low training time (below 0.1), the model will switch to NHITS with sensible default parameters instead of searching with AutoNHITS.
While this is much faster, it is less accurate.

# Any directions for future work in subsequent versions of the handler?
Implement the UPDATE method.

Implement alternative forecasting models, other than NHITS, from the NeuralForecast package.

Support exogenous regressors that are categoric, as the current implementation only supports numeric regressors.

# Please provide a minimal SQL example that uses this ML engine (pointers to integration tests in the PR also valid)
See integration test in https://github.com/mindsdb/mindsdb/pull/4615
