package nstv.compositevmchannel.list

import nstv.compositevmchannel.compositeVM.BaseCompositeViewModel
import nstv.compositevmchannel.compositeVM.BaseViewModelDelegate
import nstv.compositevmchannel.data.model.Event

class ListCompositeViewModel(delegates: List<BaseViewModelDelegate>) : BaseCompositeViewModel(delegates) {
    init {
        send(Event.Load)
    }
}
