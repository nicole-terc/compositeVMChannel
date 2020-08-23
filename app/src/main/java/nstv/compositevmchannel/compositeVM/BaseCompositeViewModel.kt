package nstv.compositevmchannel.compositeVM

import nstv.compositevmchannel.data.model.Event
import nstv.compositevmchannel.data.model.State

abstract class BaseViewModelDelegate : ViewModelDelegate<State, Event>()

open class BaseCompositeViewModel(delegates: List<BaseViewModelDelegate>) : CompositeViewModel<State, Event>(delegates)
