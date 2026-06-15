/*
 * galvanized_nail.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:43 by COMSOL 6.3.0.290. */
public class galvanized_nail {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Corrosion_Module\\Galvanic_Corrosion");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("cd", "SecondaryCurrentDistribution", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/cd", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("L_nail", "30[mm]", "\u94c1\u9489\u957f\u5ea6");
    model.param().set("sigma", "0.005[S/m]", "\u7535\u89e3\u8d28\u7535\u5bfc\u7387");
    model.param().set("Eeq_Zn", "-0.763[V]", "Zn \u5e73\u8861\u7535\u4f4d");
    model.param().set("i0_Zn", "1e-3[A/m^2]", "\u950c\u6c27\u5316\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6");
    model.param().set("A_Zn", "0.12[V]", "\u950c\u6c27\u5316\u7684 Tafel \u659c\u7387");
    model.param().set("Eeq_O2", "1.229[V]", "\u6c27\u8fd8\u539f\u5e73\u8861\u7535\u4f4d");
    model.param()
         .set("i0_O2_on_Zn", "1e-10[A/m^2]", "\u950c\u7684\u6c27\u8fd8\u539f\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6");
    model.param().set("A_O2_on_Zn", "-0.25[V]", "\u950c\u7684\u6c27\u8fd8\u539f Tafel \u659c\u7387");
    model.param()
         .set("i0_O2_on_Fe", "1e-8[A/m^2]", "\u94c1\u7684\u6c27\u8fd8\u539f\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6");
    model.param().set("A_O2_on_Fe", "-0.25[V]", "\u94c1\u7684\u6c27\u8fd8\u539f Tafel \u659c\u7387");
    model.param().set("Eeq_Fe", "-0.409[V]", "\u94c1\u6c27\u5316\u5e73\u8861\u7535\u4f4d");
    model.param().set("i0_Fe", "1e-4[A/m^2]", "\u94c1\u6c27\u5316\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6");
    model.param().set("A_Fe", "0.41[V]", "\u94c1\u6c27\u5316\u7684 Tafel \u659c\u7387");
    model.param()
         .set("D_O2", "1e-7[m^2/s]", "\u6e7f\u6728\u6750\u4e2d\u7684\u6709\u6548\u6c27\u6269\u6563\u7cfb\u6570");
    model.param().set("c_O2_ref", "0.2[mol/m^3]", "\u6c27\u53c2\u8003\u6d53\u5ea6");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("sq1", "Square");
    model.component("comp1").geom("geom1").feature("sq1").set("size", "1.5*L_nail");
    model.component("comp1").geom("geom1").feature("sq1").set("pos", new String[]{"0", "-1.5*L_nail"});
    model.component("comp1").geom("geom1").run("sq1");
    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 0, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 0, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 5, 1, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 1, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 5, 2, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", -1, 2, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 1, 3, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", -1, 3, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 1, 4, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "5-L_nail", 4, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 5, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "-L_nail", 5, 1);
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("sq1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("pol1");
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").selection().create("sel1", "Explicit");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("sel1").label("\u950c\u8868\u9762");
    model.component("comp1").selection("sel1").geom(1);
    model.component("comp1").selection("sel1").set(4, 5, 6);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u94c1\u8868\u9762");
    model.component("comp1").selection("sel2").geom(1);
    model.component("comp1").selection("sel2").set(3);
    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").label("\u94c1\u9489\u8868\u9762");
    model.component("comp1").selection("uni1").set("entitydim", 1);
    model.component("comp1").selection("uni1").set("input", new String[]{"sel1", "sel2"});

    model.component("comp1").physics("cd").feature("ice1").set("sigmal_mat", "userdef");
    model.component("comp1").physics("cd").feature("ice1")
         .set("sigmal", new String[]{"sigma", "0", "0", "0", "sigma", "0", "0", "0", "sigma"});
    model.component("comp1").physics("cd").create("es1", "ElectrodeSurface", 1);
    model.component("comp1").physics("cd").feature("es1").selection().named("sel1");
    model.component("comp1").physics("cd").feature("es1").feature("er1").label("\u950c\u53cd\u5e94");
    model.component("comp1").physics("cd").feature("es1").feature("er1").set("Eeq", "Eeq_Zn");
    model.component("comp1").physics("cd").feature("es1").feature("er1")
         .set("ElectrodeKinetics", "AnodicTafelEquation");
    model.component("comp1").physics("cd").feature("es1").feature("er1").set("i0", "i0_Zn");
    model.component("comp1").physics("cd").feature("es1").feature("er1").set("Aa", "A_Zn");
    model.component("comp1").physics("cd").feature("es1").create("er2", "ElectrodeReaction", 1);
    model.component("comp1").physics("cd").feature("es1").feature("er2").label("\u6c27\u53cd\u5e94");
    model.component("comp1").physics("cd").feature("es1").feature("er2").set("Eeq", "Eeq_O2");
    model.component("comp1").physics("cd").feature("es1").feature("er2")
         .set("ElectrodeKinetics", "CathodicTafelEquation");
    model.component("comp1").physics("cd").feature("es1").feature("er2").set("i0", "i0_O2_on_Zn");
    model.component("comp1").physics("cd").feature("es1").feature("er2").set("Ac", "A_O2_on_Zn");
    model.component("comp1").physics("cd").feature().duplicate("es2", "es1");
    model.component("comp1").physics("cd").feature("es2").selection().named("sel2");
    model.component("comp1").physics("cd").feature("es2").feature("er1").label("\u94c1\u53cd\u5e94");
    model.component("comp1").physics("cd").feature("es2").feature("er1").set("Eeq", "Eeq_Fe");
    model.component("comp1").physics("cd").feature("es2").feature("er1").set("i0", "i0_Fe");
    model.component("comp1").physics("cd").feature("es2").feature("er1").set("Aa", "A_Fe");
    model.component("comp1").physics("cd").feature("es2").feature("er2").set("i0", "i0_O2_on_Fe");
    model.component("comp1").physics("cd").feature("es2").feature("er2").set("Ac", "A_O2_on_Fe");
    model.component("comp1").physics("cd").feature("init1").set("phil", "-Eeq_Zn");

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().named("uni1");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hauto", 1);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").selection().geom("geom1", 0);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").selection().set(3);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("hmax", 0.1);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").set("data", "dset1");
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("hasspacevars", true);
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").label("\u7535\u89e3\u8d28\u7535\u4f4d (cd)");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"phil"});
    model.result("pg1").create("str1", "Streamline");
    model.result("pg1").feature("str1").set("expr", new String[]{"cd.Ilr", "cd.Ilz"});
    model.result("pg1").feature("str1").set("posmethod", "uniform");
    model.result("pg1").feature("str1").set("recover", "pprint");
    model.result("pg1").feature("str1").set("pointtype", "arrow");
    model.result("pg1").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg1").feature("str1").set("color", "gray");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "rev1");
    model.result("pg2").label("\u7535\u89e3\u8d28\u7535\u4f4d, 3D (cd)");
    model.result("pg2").create("mslc1", "Multislice");
    model.result("pg2").feature("mslc1").set("expr", new String[]{"phil"});
    model.result("pg2").create("str1", "Streamline");
    model.result("pg2").feature("str1").set("revcoordsys", "cylindrical");
    model.result("pg2").feature("str1").set("expr", new String[]{"cd.Ilr", "cd.Ilphi", "cd.Ilz"});
    model.result("pg2").feature("str1").set("pointtype", "arrow");
    model.result("pg2").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg2").feature("str1").set("color", "gray");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").label("\u7535\u89e3\u8d28\u7535\u6d41\u5bc6\u5ea6 (cd)");
    model.result("pg3").create("str1", "Streamline");
    model.result("pg3").feature("str1").set("expr", new String[]{"cd.Ilr", "cd.Ilz"});
    model.result("pg3").feature("str1").set("posmethod", "uniform");
    model.result("pg3").feature("str1").set("recover", "pprint");
    model.result("pg3").feature("str1").set("pointtype", "arrow");
    model.result("pg3").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg3").feature("str1").set("color", "gray");
    model.result("pg3").feature("str1").create("col1", "Color");
    model.result("pg3").feature("str1").feature("col1").set("expr", "root.comp1.cd.IlMag");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "rev1");
    model.result("pg4").label("\u7535\u89e3\u8d28\u7535\u6d41\u5bc6\u5ea6, 3D (cd)");
    model.result("pg4").create("str1", "Streamline");
    model.result("pg4").feature("str1").set("revcoordsys", "cylindrical");
    model.result("pg4").feature("str1").set("expr", new String[]{"cd.Ilr", "cd.Ilphi", "cd.Ilz"});
    model.result("pg4").feature("str1").set("pointtype", "arrow");
    model.result("pg4").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg4").feature("str1").set("color", "gray");
    model.result("pg4").feature("str1").create("col1", "Color");
    model.result("pg4").feature("str1").feature("col1").set("expr", "root.comp1.cd.IlMag");
    model.result("pg3").create("line1", "Line");
    model.result("pg3").feature("line1").set("expr", new String[]{"abs(cd.itot)"});
    model.result("pg3").feature("line1").set("linetype", "tube");
    model.result("pg3").feature("line1").set("inherittubescale", false);
    model.result("pg3").feature("line1").set("inheritplot", "str1");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"abs(cd.itot)"});
    model.result("pg4").feature("surf1").set("inheritplot", "str1");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").set("data", "dset1");
    model.result("pg5").label("\u7535\u6781\u7535\u4f4d vs. \u76f8\u90bb\u53c2\u6bd4\u7535\u4f4d (cd)");
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").set("data", "rev1");
    model.result("pg6").label("\u7535\u6781\u7535\u4f4d vs. \u76f8\u90bb\u53c2\u6bd4\u7535\u4f4d, 3D (cd)");
    model.result("pg5").create("str1", "Streamline");
    model.result("pg5").feature("str1").set("expr", new String[]{"cd.Ilr", "cd.Ilz"});
    model.result("pg5").feature("str1").set("posmethod", "uniform");
    model.result("pg5").feature("str1").set("recover", "pprint");
    model.result("pg5").feature("str1").set("pointtype", "arrow");
    model.result("pg5").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg5").feature("str1").set("color", "gray");
    model.result("pg6").create("str1", "Streamline");
    model.result("pg6").feature("str1").set("revcoordsys", "cylindrical");
    model.result("pg6").feature("str1").set("expr", new String[]{"cd.Ilr", "cd.Ilphi", "cd.Ilz"});
    model.result("pg6").feature("str1").set("pointtype", "arrow");
    model.result("pg6").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg6").feature("str1").set("color", "gray");
    model.result("pg5").create("line1", "Line");
    model.result("pg5").feature("line1").set("expr", new String[]{"cd.Evsref"});
    model.result("pg5").feature("line1").set("linetype", "tube");
    model.result("pg5").feature("line1").set("inherittubescale", false);
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", new String[]{"cd.Evsref"});
    model.result("pg1").run();
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").label("\u5c40\u90e8\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg7").create("lngr1", "LineGraph");
    model.result("pg7").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg7").feature("lngr1").set("linewidth", "preference");
    model.result("pg7").feature("lngr1").selection().named("sel1");
    model.result("pg7").feature("lngr1").set("expr", "cd.iloc_er1");
    model.result("pg7").feature("lngr1").set("descr", "\u5c40\u90e8\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg7").feature("lngr1").set("descractive", true);
    model.result("pg7").feature("lngr1").set("descr", "\u950c\u6c27\u5316");
    model.result("pg7").feature("lngr1").set("xdata", "expr");
    model.result("pg7").feature("lngr1").set("xdataexpr", "z");
    model.result("pg7").feature("lngr1").set("legend", true);
    model.result("pg7").feature("lngr1").set("autosolution", false);
    model.result("pg7").feature("lngr1").set("autodescr", true);
    model.result("pg7").feature().duplicate("lngr2", "lngr1");
    model.result("pg7").run();
    model.result("pg7").feature("lngr2").selection().named("sel2");
    model.result("pg7").feature("lngr2").set("descr", "\u94c1\u6c27\u5316");
    model.result("pg7").feature("lngr2").set("titletype", "none");
    model.result("pg7").feature().duplicate("lngr3", "lngr2");
    model.result("pg7").run();
    model.result("pg7").feature("lngr3").selection().named("uni1");
    model.result("pg7").feature("lngr3").set("expr", "cd.iloc_er2");
    model.result("pg7").feature("lngr3").set("descr", "\u6c27\u8fd8\u539f");
    model.result("pg7").run();
    model.result("pg7").set("legendpos", "lowerright");

    model.component("comp1").physics().create("tds", "DilutedSpecies", "geom1");

    model.study("std1").feature("stat").setSolveFor("/physics/tds", true);

    model.component("comp1").physics("tds").prop("TransportMechanism").set("Convection", false);
    model.component("comp1").physics("tds").feature("cdm1")
         .set("D_c", new String[]{"D_O2", "0", "0", "0", "D_O2", "0", "0", "0", "D_O2"});
    model.component("comp1").physics("tds").create("conc1", "Concentration", 1);
    model.component("comp1").physics("tds").feature("conc1").selection().set(7);
    model.component("comp1").physics("tds").feature("conc1").setIndex("species", true, 0);
    model.component("comp1").physics("tds").feature("conc1").setIndex("c0", "c_O2_ref", 0);
    model.component("comp1").physics("tds").feature("init1").setIndex("initc", "c_O2_ref", 0);
    model.component("comp1").physics("tds").create("eeic1", "ElectrodeElectrolyteInterfaceCoupling", 1);
    model.component("comp1").physics("tds").feature("eeic1").selection().named("sel1");
    model.component("comp1").physics("tds").feature("eeic1").feature("rc1")
         .set("iloc_src", "root.comp1.cd.es1.er2.iloc");
    model.component("comp1").physics("tds").feature("eeic1").feature("rc1").set("nm", 4);
    model.component("comp1").physics("tds").feature("eeic1").feature("rc1").setIndex("Vib", -1, 0);
    model.component("comp1").physics("tds").feature().duplicate("eeic2", "eeic1");
    model.component("comp1").physics("tds").feature("eeic2").selection().named("sel2");
    model.component("comp1").physics("tds").feature("eeic2").feature("rc1")
         .set("iloc_src", "root.comp1.cd.es2.er2.iloc");
    model.component("comp1").physics("cd").feature("es1").feature("er2").set("i0", "i0_O2_on_Zn*c/c_O2_ref");
    model.component("comp1").physics("cd").feature("es2").feature("er2").set("i0", "i0_O2_on_Fe*c/c_O2_ref");

    model.study("std1").feature("stat").setSolveFor("/physics/tds", false);
    model.study("std1").create("stat2", "Stationary");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").run();
    model.result("pg8").label("\u6c27\u6c14\u6d53\u5ea6");
    model.result("pg8").create("surf1", "Surface");
    model.result("pg8").feature("surf1").set("expr", "c");
    model.result("pg8").feature("surf1").set("descr", "\u6469\u5c14\u6d53\u5ea6\uff0cc");
    model.result("pg8").run();
    model.result().create("pg9", "PlotGroup1D");
    model.result("pg9").run();
    model.result("pg9").label("\u94c1\u6c27\u5316\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg9").create("lngr1", "LineGraph");
    model.result("pg9").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg9").feature("lngr1").set("linewidth", "preference");
    model.result("pg9").feature("lngr1").selection().named("sel2");
    model.result("pg9").feature("lngr1").set("expr", "cd.iloc_er1");
    model.result("pg9").feature("lngr1").set("descr", "\u5c40\u90e8\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg9").feature("lngr1").set("xdata", "expr");
    model.result("pg9").feature("lngr1").set("xdataexpr", "z");
    model.result("pg9").feature("lngr1").set("data", "dset1");
    model.result("pg9").feature("lngr1").set("legend", true);
    model.result("pg9").feature("lngr1").set("legendmethod", "manual");
    model.result("pg9").feature("lngr1")
         .setIndex("legends", "\u4e09\u6b21\u7535\u6d41\u5206\u5e03\uff08\u6c27\u6c14\u4f20\u9012\u53d7\u9650\uff09", 0);
    model.result("pg9").feature().duplicate("lngr2", "lngr1");
    model.result("pg9").run();
    model.result("pg9").feature("lngr2").set("data", "dset2");
    model.result("pg9").feature("lngr2")
         .setIndex("legends", "\u4e8c\u6b21\u7535\u6d41\u5206\u5e03\uff08\u65e0\u6c27\u6c14\u4f20\u9012\u9650\u5236\uff09", 0);
    model.result("pg9").run();
    model.result("pg9").set("legendpos", "lowerright");
    model.result("pg9").set("titletype", "label");

    model.title("\u9540\u950c\u94c1\u9489");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u5148\u5efa\u7acb\u4e00\u4e2a\u7535\u89e3\u8150\u8680\u6c60\u6765\u5bf9\u7a33\u6001\u4e8c\u6b21\u7535\u6d41\u5206\u5e03\u95ee\u9898\u5efa\u6a21\uff0c\u7136\u540e\u663e\u793a\u5982\u4f55\u5c06\u5176\u6269\u5c55\uff0c\u6dfb\u52a0\u8d28\u91cf\u4f20\u9012\uff0c\u5bf9\u4e09\u6b21\u7535\u6d41\u5206\u5e03\u5efa\u6a21\u3002\n\n\u51e0\u4f55\u7ed3\u6784\u4e3a\u4e8c\u7ef4\u8f74\u5bf9\u79f0\u3002");

    model.label("galvanized_nail.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
