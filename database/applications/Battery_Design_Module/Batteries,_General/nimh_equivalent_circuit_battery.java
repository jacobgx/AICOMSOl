/*
 * nimh_equivalent_circuit_battery.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:18 by COMSOL 6.3.0.290. */
public class nimh_equivalent_circuit_battery {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Battery_Design_Module\\Batteries,_General");

    model.component().create("comp1", true);

    model.component("comp1").physics().create("cir", "Circuit");
    model.component("comp1").physics("cir").feature("gnd1").set("Connections", new String[]{"0"});
    model.component("comp1").physics("cir").create("OCV1", "BatteryOpenCircuitVoltage");
    model.component("comp1").physics("cir").feature("OCV1").set("Connections", new String[]{"1", "0"});
    model.component("comp1").physics("cir").create("R1", "Resistor");
    model.component("comp1").physics("cir").feature("R1").set("Connections", new String[]{"1", "2"});
    model.component("comp1").physics("cir").feature("R1").set("R", new String[]{"1e-3[ohm]"});
    model.component("comp1").physics("cir").create("RC1", "ResistorCapacitorCouple");
    model.component("comp1").physics("cir").feature("RC1").set("Connections", new String[]{"2", "3"});
    model.component("comp1").physics("cir").create("I1", "CurrentSourceCircuit");
    model.component("comp1").physics("cir").feature("I1").set("Connections", new String[]{"3", "0"});

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/cir", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("T", "300[K]", "\u6e29\u5ea6");
    model.param()
         .set("R2", "0.786[mohm]-1.45*exp(-6[kcal/mol]/(R_const*T))[mohm]", "RC \u8026\u5408\u4e2d\u7684\u7535\u963b");
    model.param().set("tau", "15[s]", "\u65f6\u95f4\u5e38\u6570");
    model.param().set("C1", "tau/R2", "RC \u4e2d\u7684\u7535\u5bb9");
    model.param().set("R", "R_const", "\u6c14\u4f53\u5e38\u6570");
    model.param().set("i_1C", "85[A]", "1C \u7535\u6d41");
    model.param().set("SOC_0", "0.1", "\u521d\u59cb\u8377\u7535\u72b6\u6001");
    model.param().set("Q0", "85[Ah]", "\u521d\u59cb\u5bb9\u91cf");
    model.param().set("R1", "0.786[mohm]", "\u4e32\u8054\u7535\u963b");
    model.param().set("t_cycle", "2*Q0/i0*(1-SOC_0)", "\u5faa\u73af\u65f6\u95f4");
    model.param().set("t_ch_stop", "t_cycle/2", "\u5145\u7535\u65f6\u95f4");
    model.param().set("i_charge", "-i0", "\u5145\u7535\u7535\u6d41");
    model.param().set("i_disch", "i0", "\u653e\u7535\u7535\u6d41");
    model.param().set("i0", "i_1C*C_rate", "\u5916\u52a0\u7535\u6d41\u5e45\u503c");
    model.param().set("C_rate", "1", "\u500d\u7387");

    model.func().create("step1", "Step");

    model.component("comp1").variable().create("var1");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("i_load", "i_charge*ch_on+i_disch*dis_on", "\u8d1f\u8f7d\u5468\u671f");
    model.component("comp1").variable("var1").set("ch_on", "step1((t_ch_stop-t)[1/s])", "\u5f00\u59cb\u5145\u7535");
    model.component("comp1").variable("var1").set("dis_on", "step1((t-t_ch_stop)[1/s])", "\u5f00\u59cb\u653e\u7535");

    model.component("comp1").physics("cir").feature("R1").set("R", "R1");
    model.component("comp1").physics("cir").feature("OCV1").set("Q_cell0", "Q0");
    model.component("comp1").physics("cir").feature("OCV1").set("SOC_cell0", "SOC_0");
    model.component("comp1").physics("cir").feature("OCV1").set("SOC_Eocv", new int[]{});
    model.component("comp1").physics("cir").feature("OCV1").set("Eocv", new int[]{});
    model.component("comp1").physics("cir").feature("OCV1")
         .set("SOC_Eocv", new double[]{0, 0.0063, 0.0064, 0.0065, 0.0066, 0.0067, 0.0068, 0.0069, 0.007, 0.0071, 0.0072, 0.0073, 0.0074, 0.0075, 0.0076, 0.0077, 0.0078, 0.0079, 0.01, 0.0101, 0.0102, 0.0103, 0.0148, 0.0149, 0.0274, 0.0319, 0.04, 0.05, 0.0562, 0.062, 0.0651, 0.0663, 0.0702, 0.0703, 0.0772, 0.0845, 0.0927, 0.0954, 0.0955, 0.0956, 0.0957, 0.0958, 0.0959, 0.096, 0.0961, 0.0962, 0.0963, 0.0964, 0.0965, 0.0966, 0.0967, 0.0968, 0.0969, 0.097, 0.0971, 0.0972, 0.0973, 0.0974, 0.0998, 0.0999, 0.1, 0.1098, 0.115, 0.1189, 0.12, 0.1281, 0.13, 0.14, 0.1564, 0.1618, 0.1684, 0.1787, 0.1788, 0.1837, 0.189, 0.1891, 0.1892, 0.191, 0.197, 0.1999, 0.2, 0.2113, 0.2193, 0.2248, 0.2357, 0.2509, 0.251, 0.2627, 0.2628, 0.2721, 0.2861, 0.2901, 0.3, 0.3085, 0.3329, 0.3605, 0.3606, 0.3607, 0.3608, 0.3609, 0.3661, 0.3662, 0.3833, 0.3874, 0.4108, 0.4275, 0.4351, 0.4476, 0.4621, 0.5308, 0.6075, 0.6441, 0.696, 0.702, 0.7108, 0.7643, 0.7746, 0.7857, 0.8032, 0.813, 0.8222, 0.83, 0.8301, 0.8436, 0.8537, 0.8784, 0.8807, 0.9, 0.9083, 0.9225, 0.9296, 0.9366, 0.9449, 0.9502, 0.9582, 0.9654, 0.9655, 0.9656, 0.9657, 0.9755, 0.9819, 0.9885, 0.9932, 0.9933, 0.9934, 0.9935, 0.9936, 0.9937, 0.9938, 0.9939, 0.994, 0.9941, 0.9942, 0.9943, 0.9944, 0.9945, 0.9946, 0.9947, 0.9948, 0.9949, 0.995, 0.9951, 0.9952, 0.9953, 0.9954, 0.9955, 0.9956, 0.9957, 0.9958, 0.9959, 0.996, 0.9961, 0.9962, 0.9963, 0.9964, 0.9965, 0.9966, 0.9967, 0.9968, 0.9969, 0.997, 0.9971, 0.9972, 0.9973, 0.9974, 0.9975, 0.9976, 0.9977, 0.9986, 0.9991, 0.9992, 0.9993, 0.9994, 0.9995, 0.9996, 0.9997, 0.9998});
    model.component("comp1").physics("cir").feature("OCV1")
         .set("Eocv", new double[]{1.0008, 1.0518, 1.0523, 1.0527, 1.0532, 1.0537, 1.0541, 1.0546, 1.055, 1.0554, 1.0559, 1.0563, 1.0567, 1.0571, 1.0575, 1.0579, 1.0583, 1.0587, 1.0662, 1.0666, 1.0669, 1.0672, 1.0796, 1.0798, 1.1037, 1.1105, 1.1215, 1.1333, 1.14, 1.1458, 1.1489, 1.15, 1.1537, 1.1538, 1.16, 1.1662, 1.1728, 1.1749, 1.175, 1.1751, 1.1752, 1.1752, 1.1753, 1.1754, 1.1755, 1.1755, 1.1756, 1.1757, 1.1758, 1.1759, 1.1759, 1.176, 1.1761, 1.1762, 1.1762, 1.1763, 1.1764, 1.1765, 1.1783, 1.1784, 1.1784, 1.1856, 1.1893, 1.1919, 1.1927, 1.198, 1.1992, 1.2055, 1.215, 1.2179, 1.2215, 1.2267, 1.2268, 1.2292, 1.2318, 1.2318, 1.2319, 1.2327, 1.2355, 1.2368, 1.2368, 1.2418, 1.2451, 1.2473, 1.2516, 1.2571, 1.2571, 1.261, 1.2611, 1.2641, 1.2683, 1.2694, 1.2721, 1.2744, 1.2802, 1.2859, 1.2859, 1.2859, 1.286, 1.286, 1.2869, 1.287, 1.2899, 1.2906, 1.2941, 1.2962, 1.2972, 1.2986, 1.3, 1.3053, 1.3092, 1.3109, 1.3138, 1.3142, 1.3148, 1.3195, 1.3207, 1.3221, 1.3245, 1.326, 1.3276, 1.329, 1.329, 1.3317, 1.3339, 1.3401, 1.3408, 1.3469, 1.35, 1.3558, 1.3591, 1.3627, 1.3674, 1.3707, 1.3764, 1.3824, 1.3825, 1.3826, 1.3827, 1.393, 1.4019, 1.4149, 1.4294, 1.4298, 1.4302, 1.4307, 1.4311, 1.4315, 1.4319, 1.4324, 1.4328, 1.4333, 1.4338, 1.4342, 1.4347, 1.4352, 1.4357, 1.4362, 1.4367, 1.4373, 1.4378, 1.4384, 1.4389, 1.4395, 1.4401, 1.4407, 1.4413, 1.4419, 1.4425, 1.4432, 1.4439, 1.4446, 1.4453, 1.446, 1.4467, 1.4475, 1.4483, 1.4491, 1.4499, 1.4508, 1.4517, 1.4526, 1.4536, 1.4545, 1.4556, 1.4566, 1.4578, 1.4589, 1.4726, 1.4852, 1.4886, 1.4926, 1.4973, 1.5031, 1.5105, 1.5209, 1.5387});
    model.component("comp1").physics("cir").feature("I1").set("value", "i_load");
    model.component("comp1").physics("cir").feature("RC1").set("RCMode", "RC");
    model.component("comp1").physics("cir").feature("RC1").set("R", "R2");
    model.component("comp1").physics("cir").feature("RC1").set("C", "C1");

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "T", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "K", 0);
    model.study("std1").feature("param").setIndex("pname", "T", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "K", 0);
    model.study("std1").feature("param").setIndex("pname", "C_rate", 0);
    model.study("std1").feature("param").setIndex("plistarr", "2 4 8", 0);
    model.study("std1").feature("param").setIndex("punit", 1, 0);
    model.study("std1").feature("time").set("tlist", "range(0,t_cycle/1000,t_cycle)");
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").run();
    model.result("pg1").label("\u8d1f\u8f7d\u5468\u671f\u7535\u538b");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").create("glob1", "Global");
    model.result("pg1").feature("glob1").set("markerpos", "datapoints");
    model.result("pg1").feature("glob1").set("linewidth", "preference");
    model.result("pg1").feature("glob1").set("expr", new String[]{"cir.v_3"});
    model.result("pg1").feature("glob1").set("descr", new String[]{"\u8282\u70b9\u201cv_3\u201d\u7684\u7535\u538b"});
    model.result("pg1").feature("glob1").set("unit", new String[]{"V"});
    model.result("pg1").feature("glob1").set("xdata", "expr");
    model.result("pg1").feature("glob1").set("xdataexpr", "Q0*comp1.cir.OCV1.SOC");
    model.result("pg1").feature("glob1").set("xdataunit", "Ah");
    model.result("pg1").feature("glob1").set("xdatadescractive", true);
    model.result("pg1").feature("glob1").set("xdatadescr", "\u7535\u5bb9");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result().duplicate("pg2", "pg1");
    model.result("pg2").run();
    model.result("pg2").label("\u5f00\u8def\u7535\u538b\u548c SOC vs. \u65f6\u95f4");
    model.result("pg2").set("titletype", "label");
    model.result("pg2").set("twoyaxes", true);
    model.result("pg2").run();
    model.result("pg2").feature("glob1").set("expr", new String[]{"cir.OCV1.E_OCV"});
    model.result("pg2").feature("glob1").set("descr", new String[]{"\u5f00\u8def\u7535\u538b"});
    model.result("pg2").feature("glob1").set("unit", new String[]{"V"});
    model.result("pg2").feature("glob1").label("\u5f00\u8def\u7535\u538b");
    model.result("pg2").feature("glob1").set("xdata", "solution");
    model.result("pg2").run();
    model.result("pg2").feature("glob1").set("linestyle", "dashed");
    model.result("pg2").run();
    model.result("pg2").create("glob2", "Global");
    model.result("pg2").feature("glob2").set("markerpos", "datapoints");
    model.result("pg2").feature("glob2").set("linewidth", "preference");
    model.result("pg2").feature("glob2").set("expr", new String[]{"cir.OCV1.SOC"});
    model.result("pg2").feature("glob2").set("descr", new String[]{"\u8377\u7535\u72b6\u6001"});
    model.result("pg2").feature("glob2").set("unit", new String[]{"1"});
    model.result("pg2").feature("glob2").label("\u7535\u6c60\u8377\u7535\u72b6\u6001");
    model.result("pg2").feature("glob2").set("plotonsecyaxis", true);
    model.result("pg2").feature("glob2").set("linecolor", "cyclereset");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").set("showlegends", false);
    model.result("pg2").run();
    model.result("pg2").feature("glob1").set("linestyle", "solid");
    model.result("pg2").run();
    model.result("pg2").feature("glob2").active(false);
    model.result("pg2").run();
    model.result("pg2").set("twoyaxes", false);
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature("glob2").active(true);
    model.result("pg2").run();
    model.result("pg2").set("twoyaxes", true);
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature("glob1").set("linestyle", "dashed");
    model.result("pg2").run();

    model.title("\u954d\u6c22\u7535\u6c60\u7684\u7b49\u6548\u7535\u8def\u6a21\u578b");

    model
         .description("\u672c\u4f8b\u4f7f\u7528\u7b49\u6548\u7535\u8def\u6a21\u578b\u6765\u6a21\u62df\u954d\u6c22\u7535\u6c60\u3002\u6a21\u578b\u5305\u542b\u4e24\u4e2a\u7535\u963b\u5668\u3001\u4e00\u4e2a\u7535\u5bb9\u5668\u3001\u4e00\u4e2a\u7535\u6d41\u6e90\u4ee5\u53ca\u4e00\u4e2a\u4e0e\u7535\u6c60\u8377\u7535\u72b6\u6001 (SOC) \u76f8\u5173\u7684\u7535\u538b\u6e90\u3002\u901a\u8fc7\u53c2\u6570\u5316\u626b\u63cf\uff0c\u6a21\u62df\u4e86\u4e0d\u540c\u500d\u7387\u4e0b\u7684\u5404\u79cd\u5145\u653e\u7535\u5faa\u73af\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();

    model.label("nimh_equivalent_circuit_battery.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
