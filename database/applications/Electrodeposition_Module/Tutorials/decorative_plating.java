/*
 * decorative_plating.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:57 by COMSOL 6.3.0.290. */
public class decorative_plating {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Electrodeposition_Module\\Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("cd", "SecondaryCurrentDistribution", "geom1");

    model.study().create("std1");
    model.study("std1").create("cdi", "CurrentDistributionInitialization");
    model.study("std1").feature("cdi").set("ftplistmethod", "manual");
    model.study("std1").feature("cdi").set("solnum", "auto");
    model.study("std1").feature("cdi").set("notsolnum", "auto");
    model.study("std1").feature("cdi").set("outputmap", new String[]{});
    model.study("std1").feature("cdi").set("ngenAUX", "1");
    model.study("std1").feature("cdi").set("goalngenAUX", "1");
    model.study("std1").feature("cdi").set("ngenAUX", "1");
    model.study("std1").feature("cdi").set("goalngenAUX", "1");
    model.study("std1").feature("cdi").setSolveFor("/physics/cd", true);
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").set("ftplistmethod", "manual");
    model.study("std1").feature("time").set("initialtime", "0");
    model.study("std1").feature("time").set("solnum", "auto");
    model.study("std1").feature("time").set("notsolnum", "auto");
    model.study("std1").feature("time").set("outputmap", new String[]{});
    model.study("std1").feature("time").setSolveFor("/physics/cd", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("Eeq_Ni", "-0.26[V]", "\u5e73\u8861\u7535\u4f4d\uff0c\u954d\u53cd\u5e94");
    model.param().set("Iavg", "-5[A/dm^2]", "\u5e73\u5747\u9634\u6781\u7535\u6d41\u5bc6\u5ea6");
    model.param().set("kappa", "10[S/m]", "\u7535\u89e3\u8d28\u7535\u5bfc\u7387");
    model.param().set("M_Ni", "59[g/mole]", "\u6469\u5c14\u8d28\u91cf\uff0c\u954d");
    model.param().set("rho_Ni", "8900[kg/m^3]", "\u5bc6\u5ea6\uff0c\u954d");
    model.param().set("i0_Ni", "0.1[A/m^2]", "\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u954d\u53cd\u5e94");
    model.param().set("i0_H", "2e-5[A/m^2]", "\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u6c22\u53cd\u5e94");

    model.component("comp1").geom("geom1").insertFile("decorative_plating_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").physics("cd").feature("ice1").set("sigmal_mat", "userdef");
    model.component("comp1").physics("cd").feature("ice1")
         .set("sigmal", new String[]{"kappa", "0", "0", "0", "kappa", "0", "0", "0", "kappa"});
    model.component("comp1").physics("cd").create("es1", "ElectrodeSurface", 2);
    model.component("comp1").physics("cd").feature("es1").selection().set(4);
    model.component("comp1").physics("cd").feature("es1").setIndex("Species", "s1", 0, 0);
    model.component("comp1").physics("cd").feature("es1").setIndex("rhos", 8960, 0, 0);
    model.component("comp1").physics("cd").feature("es1").setIndex("Ms", 0.06355, 0, 0);
    model.component("comp1").physics("cd").feature("es1").setIndex("Species", "s1", 0, 0);
    model.component("comp1").physics("cd").feature("es1").setIndex("rhos", 8960, 0, 0);
    model.component("comp1").physics("cd").feature("es1").setIndex("Ms", 0.06355, 0, 0);
    model.component("comp1").physics("cd").feature("es1").setIndex("Species", "Ni", 0, 0);
    model.component("comp1").physics("cd").feature("es1").setIndex("rhos", "rho_Ni", 0, 0);
    model.component("comp1").physics("cd").feature("es1").setIndex("Ms", "M_Ni", 0, 0);
    model.component("comp1").physics("cd").feature("es1").feature("er1").set("nm", 2);
    model.component("comp1").physics("cd").feature("es1").feature("er1").setIndex("Vib", 1, 0, 0);
    model.component("comp1").physics("cd").feature("es1").feature("er1").set("Eeq", "Eeq_Ni");
    model.component("comp1").physics("cd").feature("es1").feature("er1").set("ElectrodeKinetics", "ButlerVolmer");
    model.component("comp1").physics("cd").feature("es1").feature("er1").set("i0", "i0_Ni");
    model.component("comp1").physics("cd").feature().duplicate("es2", "es1");
    model.component("comp1").physics("cd").feature("es2").selection().named("geom1_boxsel1");
    model.component("comp1").physics("cd").feature("es2").set("BoundaryCondition", "AverageCurrentDensity");
    model.component("comp1").physics("cd").feature("es2").set("Ial", "Iavg");
    model.component("comp1").physics("cd").feature("es2").feature("er1").setIndex("Vib", 1, 0, 0);
    model.component("comp1").physics("cd").feature("es2").create("er2", "ElectrodeReaction", 2);
    model.component("comp1").physics("cd").feature("es2").feature("er2")
         .set("ElectrodeKinetics", "CathodicTafelEquation");
    model.component("comp1").physics("cd").feature("es2").feature("er2").set("i0", "i0_H");

    model.component("comp1").mesh("mesh1").autoMeshSize(4);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("cdi").set("initType", "secondary");
    model.study("std1").feature("time").set("tlist", "range(0,60,600)");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").create("glob1", "Global");
    model.result("pg1").feature("glob1").set("unit", new String[]{""});
    model.result("pg1").feature("glob1").set("expr", new String[]{"cd.phisext_es2"});
    model.result("pg1").feature("glob1").set("descr", new String[]{"\u5916\u90e8\u7535\u4f4d"});
    model.result("pg1").label("\u5bf9\u5730\u8fb9\u754c\u7535\u6781\u7535\u4f4d (cd)");
    model.result("pg1").feature("glob1").set("xdatasolnumtype", "level1");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 11, 0);
    model.result("pg2").label("\u7535\u89e3\u8d28\u7535\u4f4d (cd)");
    model.result("pg2").create("mslc1", "Multislice");
    model.result("pg2").feature("mslc1").set("expr", new String[]{"phil"});
    model.result("pg2").create("str1", "Streamline");
    model.result("pg2").feature("str1").set("expr", new String[]{"cd.Ilx", "cd.Ily", "cd.Ilz"});
    model.result("pg2").feature("str1").set("posmethod", "start");
    model.result("pg2").feature("str1").set("pointtype", "arrow");
    model.result("pg2").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg2").feature("str1").set("color", "gray");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").setIndex("looplevel", 11, 0);
    model.result("pg3").label("\u7535\u89e3\u8d28\u7535\u6d41\u5bc6\u5ea6 (cd)");
    model.result("pg3").create("str1", "Streamline");
    model.result("pg3").feature("str1").set("expr", new String[]{"cd.Ilx", "cd.Ily", "cd.Ilz"});
    model.result("pg3").feature("str1").set("posmethod", "start");
    model.result("pg3").feature("str1").set("pointtype", "arrow");
    model.result("pg3").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg3").feature("str1").set("color", "gray");
    model.result("pg3").feature("str1").create("col1", "Color");
    model.result("pg3").feature("str1").feature("col1").set("expr", "root.comp1.cd.IlMag");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"abs(cd.itot)"});
    model.result("pg3").feature("surf1").set("inheritplot", "str1");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").setIndex("looplevel", 11, 0);
    model.result("pg4").label("\u5bf9\u5730\u7535\u6781\u7535\u4f4d (cd)");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"cd.phisext"});
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").set("data", "dset1");
    model.result("pg5").setIndex("looplevel", 11, 0);
    model.result("pg5").label("\u7535\u6781\u7535\u4f4d vs. \u76f8\u90bb\u53c2\u6bd4\u7535\u4f4d (cd)");
    model.result("pg5").create("str1", "Streamline");
    model.result("pg5").feature("str1").set("expr", new String[]{"cd.Ilx", "cd.Ily", "cd.Ilz"});
    model.result("pg5").feature("str1").set("posmethod", "start");
    model.result("pg5").feature("str1").set("pointtype", "arrow");
    model.result("pg5").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg5").feature("str1").set("color", "gray");
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", new String[]{"cd.Evsref"});
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").set("data", "dset1");
    model.result("pg6").setIndex("looplevel", 11, 0);
    model.result("pg6").label("\u7535\u6781\u603b\u539a\u5ea6\u53d8\u5316 (cd)");
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", new String[]{"cd.sbtot"});
    model.result("pg6").feature("surf1").set("unit", "\u00b5m");
    model.result("pg1").run();
    model.result("pg6").run();
    model.result("pg6").selection().geom("geom1", 2);
    model.result("pg6").selection().named("geom1_boxsel1");
    model.result("pg6").run();
    model.result("pg6").label("\u6c89\u79ef\u539a\u5ea6\uff0c\u9634\u6781");
    model.result().duplicate("pg7", "pg6");
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg7").feature("surf1").set("expr", "cd.iloc_er1");
    model.result("pg7").feature("surf1").set("descr", "\u5c40\u90e8\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg7").feature("surf1").set("expr", "cd.iloc_er1/cd.itot");
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg7").label("\u7535\u6d41\u6548\u7387\uff0c\u9634\u6781");

    model.title("\u88c5\u9970\u9540\u5c42");

    model
         .description("\u672c\u7535\u9540\u6559\u5b66\u6a21\u578b\u4f7f\u7528\u4e8c\u6b21\u7535\u6d41\u5206\u5e03\uff0c\u540c\u65f6\u5728\u9633\u6781\u548c\u9634\u6781\u90fd\u5e94\u7528\u4e86\u5b8c\u6574\u7684 Butler-Volmer \u52a8\u529b\u5b66\u539f\u7406\uff0c\u53ef\u4ee5\u8ba1\u7b97\u9634\u6781\u6c89\u79ef\u5c42\u7684\u539a\u5ea6\u4ee5\u53ca\u9633\u6781\u8868\u9762\u7684\u6eb6\u89e3\u4ea7\u751f\u7684\u56fe\u6848\u3002");

    model.label("decorative_plating.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
