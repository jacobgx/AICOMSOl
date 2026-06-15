/*
 * cathodic_protection_in_concrete.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:41 by COMSOL 6.3.0.290. */
public class cathodic_protection_in_concrete {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Corrosion_Module\\Cathodic_Protection");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("tcd", "TertiaryCurrentDistributionNernstPlanck", "geom1");
    model.component("comp1").physics("tcd").prop("SpeciesProperties")
         .set("ChargeTransportModel", "SupportingElectrolyte");
    model.component("comp1").physics("tcd").field("concentration").field("c");
    model.component("comp1").physics("tcd").field("concentration").component(new String[]{"c"});

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/tcd", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("A_Fe", "0.41[V]", "\u94c1\u6c27\u5316\u7684 Tafel \u659c\u7387");
    model.param().set("A_H2", "-0.15[V]", "\u6790\u6c22\u7684 Tafel \u659c\u7387");
    model.param().set("A_O2", "-0.18[V]", "\u6c27\u8fd8\u539f\u7684 Tafel \u659c\u7387");
    model.param().set("C_O2_ref", "8.6[mol/m^3]", "\u6c27\u53c2\u8003\u6d53\u5ea6");
    model.param().set("Eeq_Fe", "-0.76[V]", "\u94c1\u6c27\u5316\u5e73\u8861\u7535\u4f4d");
    model.param().set("Eeq_H2", "-1.03[V]", "\u6790\u6c22\u5e73\u8861\u7535\u4f4d");
    model.param().set("Eeq_O2", "0.189[V]", "\u6c27\u8fd8\u539f\u5e73\u8861\u7535\u4f4d");
    model.param().set("i0_Fe", "7.1e-5[A/m^2]", "\u94c1\u6c27\u5316\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6");
    model.param().set("i0_H2", "1.1e-2[A/m^2]", "\u6790\u6c22\u7535\u6d41\u5bc6\u5ea6");
    model.param().set("i0_O2", "7.7e-7[A/m^2]", "\u6c27\u8fd8\u539f\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6");
    model.param().set("Eeq_Zn", "-0.68[V]", "Zn \u5e73\u8861\u7535\u4f4d");
    model.param().set("L", "3.175e-2[m]", "\u6df7\u51dd\u571f\u622a\u9762\u957f\u5ea6");
    model.param().set("R_rebar", "0.635e-2[m]", "\u94a2\u7b4b\u534a\u5f84");
    model.param().set("S", "2.54e-2[m]", "\u6df7\u51dd\u571f\u539a\u5ea6");
    model.param().set("W", "6.35e-2[m]", "\u6df7\u51dd\u571f\u622a\u9762\u5bbd\u5ea6");
    model.param().set("PS", "0.6", "\u5b54\u9699\u9971\u548c\u5ea6");
    model.param().set("E_app", "-1[V]", "\u5916\u52a0\u7535\u4f4d");

    model.component("comp1").func().create("int1", "Interpolation");
    model.component("comp1").func("int1").set("source", "file");
    model.component("comp1").func("int1").set("filename", "cathodic_protection_in_concrete_sigma.txt");
    model.component("comp1").func("int1").importData();
    model.component("comp1").func("int1").set("funcname", "sigma");
    model.component("comp1").func("int1").setIndex("fununit", "S/m", 0);
    model.component("comp1").func().create("int2", "Interpolation");
    model.component("comp1").func("int2").set("source", "file");
    model.component("comp1").func("int2").set("filename", "cathodic_protection_in_concrete_D_O2.txt");
    model.component("comp1").func("int2").importData();
    model.component("comp1").func("int2").set("funcname", "D_O2");
    model.component("comp1").func("int2").setIndex("fununit", "m^2/s", 0);

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"W", "L"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", "R_rebar");
    model.component("comp1").geom("geom1").feature("c1").set("pos", new String[]{"S+R_rebar", "L"});
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("c1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").physics("tcd").feature("ice1")
         .set("D_c", new String[]{"D_O2(PS)", "0", "0", "0", "D_O2(PS)", "0", "0", "0", "D_O2(PS)"});
    model.component("comp1").physics("tcd").feature("ice1").set("sigmal_mat", "userdef");
    model.component("comp1").physics("tcd").feature("ice1")
         .set("sigmal", new String[]{"sigma(PS)", "0", "0", "0", "sigma(PS)", "0", "0", "0", "sigma(PS)"});
    model.component("comp1").physics("tcd").feature("init1").setIndex("initc", "C_O2_ref", 0);
    model.component("comp1").physics("tcd").create("es1", "ElectrodeSurface", 1);
    model.component("comp1").physics("tcd").feature("es1").selection().set(1);
    model.component("comp1").physics("tcd").feature("es1").feature("er1").label("\u950c\u6c27\u5316");
    model.component("comp1").physics("tcd").feature("es1").feature("er1").set("Eeq_mat", "userdef");
    model.component("comp1").physics("tcd").feature("es1").feature("er1").set("Eeq", "Eeq_Zn");
    model.component("comp1").physics("tcd").feature("es1").feature("er1")
         .set("ElectrodeKinetics", "ThermodynamicEquilibrium");
    model.component("comp1").physics("tcd").create("es2", "ElectrodeSurface", 1);
    model.component("comp1").physics("tcd").feature("es2").selection().set(6, 7);
    model.component("comp1").physics("tcd").feature("es2").set("phisext0", "E_app");
    model.component("comp1").physics("tcd").feature("es2").feature("er1").label("\u6c27\u8fd8\u539f");
    model.component("comp1").physics("tcd").feature("es2").feature("er1").set("nm", 4);
    model.component("comp1").physics("tcd").feature("es2").feature("er1").setIndex("Vi0", -1, 0);
    model.component("comp1").physics("tcd").feature("es2").feature("er1").set("Eeq_mat", "userdef");
    model.component("comp1").physics("tcd").feature("es2").feature("er1").set("Eeq", "Eeq_O2");
    model.component("comp1").physics("tcd").feature("es2").feature("er1")
         .set("ElectrodeKinetics", "CathodicTafelEquation");
    model.component("comp1").physics("tcd").feature("es2").feature("er1").set("i0", "c/C_O2_ref*i0_O2");
    model.component("comp1").physics("tcd").feature("es2").feature("er1").set("Ac", "A_O2");
    model.component("comp1").physics("tcd").feature("es2").create("er2", "ElectrodeReaction", 1);
    model.component("comp1").physics("tcd").feature("es2").feature("er2").label("\u94c1\u6c27\u5316");
    model.component("comp1").physics("tcd").feature("es2").feature("er2").set("Eeq_mat", "userdef");
    model.component("comp1").physics("tcd").feature("es2").feature("er2").set("Eeq", "Eeq_Fe");
    model.component("comp1").physics("tcd").feature("es2").feature("er2").set("i0", "i0_Fe");
    model.component("comp1").physics("tcd").feature("es2").feature("er2")
         .set("ElectrodeKinetics", "AnodicTafelEquation");
    model.component("comp1").physics("tcd").feature("es2").feature("er2").set("Aa", "A_Fe");
    model.component("comp1").physics("tcd").feature("es2").create("er3", "ElectrodeReaction", 1);
    model.component("comp1").physics("tcd").feature("es2").feature("er3").label("\u6790\u6c22");
    model.component("comp1").physics("tcd").feature("es2").feature("er3").set("Eeq_mat", "userdef");
    model.component("comp1").physics("tcd").feature("es2").feature("er3").set("Eeq", "Eeq_H2");
    model.component("comp1").physics("tcd").feature("es2").feature("er3").set("i0", "i0_H2");
    model.component("comp1").physics("tcd").feature("es2").feature("er3")
         .set("ElectrodeKinetics", "CathodicTafelEquation");
    model.component("comp1").physics("tcd").feature("es2").feature("er3").set("Ac", "A_H2");
    model.component("comp1").physics("tcd").create("conc1", "Concentration", 1);
    model.component("comp1").physics("tcd").feature("conc1").selection().set(1);
    model.component("comp1").physics("tcd").feature("conc1").setIndex("species", true, 0);
    model.component("comp1").physics("tcd").feature("conc1").setIndex("c0", "C_O2_ref", 0);

    model.component("comp1").mesh("mesh1").autoMeshSize(2);

    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "A_Fe", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "V", 0);
    model.study("std1").feature("stat").setIndex("pname", "A_Fe", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "V", 0);
    model.study("std1").feature("stat").setIndex("pname", "PS", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(0.2, 0.05, 0.8)", 0);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 13, 0);
    model.result("pg1").label("\u7535\u89e3\u8d28\u7535\u4f4d (tcd)");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"phil"});
    model.result("pg1").create("str1", "Streamline");
    model.result("pg1").feature("str1").set("expr", new String[]{"tcd.Ilx", "tcd.Ily"});
    model.result("pg1").feature("str1").set("posmethod", "uniform");
    model.result("pg1").feature("str1").set("recover", "pprint");
    model.result("pg1").feature("str1").set("pointtype", "arrow");
    model.result("pg1").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg1").feature("str1").set("color", "gray");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 13, 0);
    model.result("pg2").label("\u7535\u89e3\u8d28\u7535\u6d41\u5bc6\u5ea6 (tcd)");
    model.result("pg2").create("str1", "Streamline");
    model.result("pg2").feature("str1").set("expr", new String[]{"tcd.Ilx", "tcd.Ily"});
    model.result("pg2").feature("str1").set("posmethod", "uniform");
    model.result("pg2").feature("str1").set("recover", "pprint");
    model.result("pg2").feature("str1").set("pointtype", "arrow");
    model.result("pg2").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg2").feature("str1").set("color", "gray");
    model.result("pg2").feature("str1").create("col1", "Color");
    model.result("pg2").feature("str1").feature("col1").set("expr", "root.comp1.tcd.IlMag");
    model.result("pg2").create("line1", "Line");
    model.result("pg2").feature("line1").set("expr", new String[]{"abs(tcd.itot)"});
    model.result("pg2").feature("line1").set("linetype", "tube");
    model.result("pg2").feature("line1").set("inherittubescale", false);
    model.result("pg2").feature("line1").set("inheritplot", "str1");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").setIndex("looplevel", 13, 0);
    model.result("pg3").label("\u5bf9\u5730\u7535\u6781\u7535\u4f4d (tcd)");
    model.result("pg3").create("line1", "Line");
    model.result("pg3").feature("line1").set("expr", new String[]{"tcd.phisext"});
    model.result("pg3").feature("line1").set("linetype", "tube");
    model.result("pg3").feature("line1").set("inherittubescale", false);
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").setIndex("looplevel", 13, 0);
    model.result("pg4").label("\u7535\u6781\u7535\u4f4d vs. \u76f8\u90bb\u53c2\u6bd4\u7535\u4f4d (tcd)");
    model.result("pg4").create("str1", "Streamline");
    model.result("pg4").feature("str1").set("expr", new String[]{"tcd.Ilx", "tcd.Ily"});
    model.result("pg4").feature("str1").set("posmethod", "uniform");
    model.result("pg4").feature("str1").set("recover", "pprint");
    model.result("pg4").feature("str1").set("pointtype", "arrow");
    model.result("pg4").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg4").feature("str1").set("color", "gray");
    model.result("pg4").create("line1", "Line");
    model.result("pg4").feature("line1").set("expr", new String[]{"tcd.Evsref"});
    model.result("pg4").feature("line1").set("linetype", "tube");
    model.result("pg4").feature("line1").set("inherittubescale", false);
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").set("data", "dset1");
    model.result("pg5").setIndex("looplevel", 13, 0);
    model.result("pg5").label("\u6d53\u5ea6 (tcd)");
    model.result("pg5").set("titletype", "custom");
    model.result("pg5").set("prefixintitle", "");
    model.result("pg5").set("expressionintitle", false);
    model.result("pg5").set("typeintitle", false);
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", new String[]{"c"});
    model.result("pg5").feature("surf1").set("colortable", "Rainbow");
    model.result("pg5").set("typeintitle", true);
    model.result("pg5").create("str1", "Streamline");
    model.result("pg5").feature("str1").set("expr", new String[]{"tcd.tflux_cx", "tcd.tflux_cy"});
    model.result("pg5").feature("str1").set("posmethod", "uniform");
    model.result("pg5").feature("str1").set("recover", "pprint");
    model.result("pg5").feature("str1").set("pointtype", "arrow");
    model.result("pg5").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg5").feature("str1").set("color", "gray");
    model.result("pg1").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("\u94a2\u7b4b\u7535\u4f4d");
    model.result("pg6").create("ptgr1", "PointGraph");
    model.result("pg6").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg6").feature("ptgr1").set("linewidth", "preference");
    model.result("pg6").feature("ptgr1").selection().set(3, 4, 5);
    model.result("pg6").feature("ptgr1").set("expr", "tcd.Evsref");
    model.result("pg6").feature("ptgr1")
         .set("descr", "\u7535\u6781\u7535\u4f4d vs. \u76f8\u90bb\u53c2\u6bd4\u7535\u4f4d");
    model.result("pg6").feature("ptgr1").set("legend", true);
    model.result("pg6").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg6").feature("ptgr1").setIndex("legends", "\u524d", 0);
    model.result("pg6").feature("ptgr1").setIndex("legends", "\u4e2d", 1);
    model.result("pg6").feature("ptgr1").setIndex("legends", "\u540e", 2);
    model.result("pg6").run();
    model.result("pg6").run();
    model.result().duplicate("pg7", "pg6");
    model.result("pg7").run();
    model.result("pg7").label("\u6c27\u6d53\u5ea6");
    model.result("pg7").run();
    model.result("pg7").feature("ptgr1").set("expr", "c");
    model.result("pg7").feature("ptgr1").set("descr", "\u6469\u5c14\u6d53\u5ea6\uff0cc");
    model.result("pg7").run();
    model.result().duplicate("pg8", "pg7");
    model.result("pg8").run();
    model.result("pg8").label("\u6c27\u8fd8\u539f\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg8").run();
    model.result("pg8").feature("ptgr1").set("expr", "tcd.iloc_er1");
    model.result("pg8").feature("ptgr1").set("descr", "\u5c40\u90e8\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg8").run();
    model.result().duplicate("pg9", "pg8");
    model.result("pg9").run();
    model.result("pg9").label("\u94c1\u6c27\u5316\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg9").run();
    model.result("pg9").feature("ptgr1").set("expr", "tcd.iloc_er2");
    model.result("pg9").feature("ptgr1").set("descr", "\u5c40\u90e8\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg9").run();
    model.result("pg9").run();
    model.result().duplicate("pg10", "pg9");
    model.result("pg10").run();
    model.result("pg10").label("\u6790\u6c22\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg10").run();
    model.result("pg10").feature("ptgr1").set("expr", "tcd.iloc_er3");
    model.result("pg10").feature("ptgr1").set("descr", "\u5c40\u90e8\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg10").run();
    model.result("pg1").run();
    model.result("pg5").run();

    model.title("\u94a2\u7b4b\u6df7\u51dd\u571f\u4e2d\u94a2\u7684\u9634\u6781\u4fdd\u62a4");

    model
         .description("\u672c\u4f8b\u5bf9\u6df7\u51dd\u571f\u4e2d\u94a2\u7b6f\u7684\u9634\u6781\u4fdd\u62a4\u5efa\u6a21\u3002\u8003\u8651\u94a2\u7b6f\u8868\u9762\u7684\u4e09\u4e2a\u4e0d\u540c\u7684\u7535\u5316\u5b66\u53cd\u5e94\u3002\u5728\u6df7\u51dd\u571f\u57df\u4e2d\u5bf9\u7535\u8377\u548c\u6c27\u4f20\u9012\u5efa\u6a21\uff0c\u5176\u4e2d\u7535\u89e3\u6db2\u7535\u5bfc\u7387\u548c\u6c27\u6269\u6563\u7387\u53d6\u51b3\u4e8e\u6e7f\u5ea6\u3002\u7814\u7a76\u4e86\u6e7f\u5ea6\u4e0d\u540c\u65f6\u7684\u8150\u8680\u7535\u6d41\u3002");

    model.label("cathodic_protection_in_concrete.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
