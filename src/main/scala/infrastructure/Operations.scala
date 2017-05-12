package infrastructure

import domain.ConsumptionItem

/**
  * Created by david.lush on 08/05/2017.
  */
object Operations {

  def avg(input: Seq[ConsumptionItem], fromHour: Int, toHour: Int): Double = {
    val range: Seq[Int] = fromHour to toHour
    val listOfHours =
      input
        .groupBy(_.timestamp.getHour)

    val filteredByHour = listOfHours.filter{x =>
      range.contains(x._1)
    }

    filteredByHour
      .flatMap(_._2.map(_.consumption_Wh)).sum / filteredByHour.size / 1000
  }

  def sum(input: Seq[ConsumptionItem], fromHour: Int, toHour: Int): Double = {
    val filtered = input.filter(_.sensor_id == id)
    val sumOfWhatts = filtered.map(_.consumption_Wh).sum / 1000
  }

}
