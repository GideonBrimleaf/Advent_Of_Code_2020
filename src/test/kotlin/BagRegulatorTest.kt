import day7.*
import kotlin.test.Test
import kotlin.test.assertEquals

class BagRegulatorTest {
    private val bagsPt1 = mutableListOf(
        "light red bags contain 1 bright white bag, 2 muted yellow bags.",
        "dark orange bags contain 3 bright white bags, 4 muted yellow bags.",
        "bright white bags contain 1 shiny gold bag.",
        "muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.",
        "shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.",
        "dark olive bags contain 3 faded blue bags, 4 dotted black bags.",
        "vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.",
        "faded blue bags contain no other bags.",
        "dotted black bags contain no other bags."
    )

    private val bagsPt2 = mutableListOf(
        "shiny gold bags contain 2 dark red bags.",
        "dark red bags contain 2 dark orange bags.",
        "dark orange bags contain 2 dark yellow bags.",
        "dark yellow bags contain 2 dark green bags.",
        "dark green bags contain 2 dark blue bags.",
        "dark blue bags contain 2 dark violet bags.",
        "dark violet bags contain no other bags."
    )

    private var referentialBags = mutableMapOf(
        "light red" to mapOf("bright white" to 1, "muted yellow" to 2),
        "dark orange" to mapOf("bright white" to 3, "muted yellow" to 4),
        "bright white" to mapOf("shiny gold" to 1),
        "muted yellow" to mapOf("shiny gold" to 2, "faded blue" to 9),
        "shiny gold" to mapOf("dark olive" to 1, "vibrant plum" to 2),
        "dark olive" to mapOf("faded blue" to 3, "dotted black" to 4),
        "vibrant plum" to mapOf("faded blue" to 5, "dotted black" to 6),
        "faded blue" to mapOf(),
        "dotted black" to mapOf()
    )

    private var truthyBags = mutableMapOf(
        "light red" to false,
        "dark orange" to false,
        "bright white" to true,
        "muted yellow" to true,
        "shiny gold" to false,
        "dark olive" to false,
        "vibrant plum" to false,
        "faded blue" to false,
        "dotted black" to false
    )

    private var partTwoBags = mapOf(
        "shiny gold" to mapOf("dark red" to 2),
        "dark red" to mapOf("dark orange" to 2),
        "dark orange" to mapOf("dark yellow" to 2),
        "dark yellow" to mapOf("dark green" to 2),
        "dark green" to mapOf("dark blue" to 2),
        "dark blue" to mapOf("dark violet" to 2),
        "dark violet" to mapOf()
    )

    private var jumbledBags = mapOf(
        "dim crimson" to mapOf(),
        "light violet" to mapOf("dim crimson" to 3, "drab brown" to 2),
        "shiny gold" to mapOf("wavy green" to 4),
        "wavy green" to mapOf("light violet" to 2),
        "drab brown" to mapOf()
    )

    @Test fun `Can create map of bag quantities` () {
        assertEquals(referentialBags, toBagQuantityData(bagsPt1))
    }

    @Test fun `Can create bag quantity map from nested data` () {
        assertEquals(partTwoBags, toBagQuantityData(bagsPt2))
    }

    @Test fun `Can create truthy map` () {
        assertEquals(truthyBags, toTruthyBags(referentialBags))
    }

    @Test fun `Can count possible gold bag containing bags` () {
        assertEquals(4, countValidExternalBags1(truthyBags, referentialBags))
    }

    @Test fun `Can count number of bags required in gold bag` () {
        assertEquals(32, countValidInternalBags("shiny gold", toBagQuantityData(bagsPt1)))
    }

    @Test fun `Can calculate multiple depth of bags` () {
        assertEquals(126, countValidInternalBags("shiny gold", toBagQuantityData(bagsPt2)))
    }

    @Test fun `Can calculate complex nested bags` () {
        assertEquals(52, countValidInternalBags("shiny gold", jumbledBags ))
    }
}