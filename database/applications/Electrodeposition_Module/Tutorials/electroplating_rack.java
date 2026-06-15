/*
 * electroplating_rack.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:58 by COMSOL 6.3.0.290. */
public class electroplating_rack {

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
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").set("ftplistmethod", "manual");
    model.study("std1").feature("stat").set("solnum", "auto");
    model.study("std1").feature("stat").set("notsolnum", "auto");
    model.study("std1").feature("stat").set("outputmap", new String[]{});
    model.study("std1").feature("stat").set("ngenAUX", "1");
    model.study("std1").feature("stat").set("goalngenAUX", "1");
    model.study("std1").feature("stat").set("ngenAUX", "1");
    model.study("std1").feature("stat").set("goalngenAUX", "1");
    model.study("std1").feature("stat").setSolveFor("/physics/cd", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("Eeq_Ni", "-0.26[V]", "\u5e73\u8861\u7535\u4f4d\uff0c\u954d\u53cd\u5e94");
    model.param().set("Iavg", "-1[A/dm^2]", "\u5e73\u5747\u9634\u6781\u7535\u6d41\u5bc6\u5ea6");
    model.param().set("sigma", "10[S/m]", "\u7535\u89e3\u8d28\u7535\u5bfc\u7387");
    model.param().set("M_Ni", "59[g/mole]", "\u6469\u5c14\u8d28\u91cf\uff0c\u954d");
    model.param().set("rho_Ni", "8900[kg/m^3]", "\u5bc6\u5ea6\uff0c\u954d");
    model.param().set("i0_Ni", "0.1[A/m^2]", "\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6\uff0c\u954d\u53cd\u5e94");
    model.param().set("PlatingTime", "10 [min]", "\u603b\u7535\u9540\u65f6\u95f4");

    model.component("comp1").geom("geom1").insertFile("electroplating_rack_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").physics("cd").selection().set(1);
    model.component("comp1").physics("cd").feature("ice1").set("sigmal_mat", "userdef");
    model.component("comp1").physics("cd").feature("ice1")
         .set("sigmal", new String[]{"sigma", "0", "0", "0", "sigma", "0", "0", "0", "sigma"});
    model.component("comp1").physics("cd").create("es1", "ElectrodeSurface", 2);
    model.component("comp1").physics("cd").feature("es1").label("\u7535\u6781\u8868\u9762 - \u9633\u6781");
    model.component("comp1").physics("cd").feature("es1").selection().set(2);
    model.component("comp1").physics("cd").feature("es1").feature("er1").set("Eeq", "Eeq_Ni");
    model.component("comp1").physics("cd").feature("es1").feature("er1").set("ElectrodeKinetics", "ButlerVolmer");
    model.component("comp1").physics("cd").feature("es1").feature("er1").set("i0", "i0_Ni");
    model.component("comp1").physics("cd").feature().duplicate("es2", "es1");
    model.component("comp1").physics("cd").feature("es2").label("\u7535\u6781\u8868\u9762 - \u9634\u6781");
    model.component("comp1").physics("cd").feature("es2").selection().named("geom1_arr1_bnd");
    model.component("comp1").physics("cd").feature("es2").set("BoundaryCondition", "AverageCurrentDensity");
    model.component("comp1").physics("cd").feature("es2").set("Ial", "Iavg");

    model.component("comp1").mesh("mesh1").autoMeshSize(4);
    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").view("view1").set("transparency", true);

    model.component("comp1").variable().create("var1");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("thickness_cathode", "abs(cd.iloc_er1)/2/F_const*M_Ni/rho_Ni*PlatingTime", "\u9634\u6781\u4e0a\u7684\u539a\u5ea6");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").label("\u7535\u89e3\u8d28\u7535\u4f4d (cd)");
    model.result("pg1").create("mslc1", "Multislice");
    model.result("pg1").feature("mslc1").set("expr", new String[]{"phil"});
    model.result("pg1").create("str1", "Streamline");
    model.result("pg1").feature("str1").set("expr", new String[]{"cd.Ilx", "cd.Ily", "cd.Ilz"});
    model.result("pg1").feature("str1").set("posmethod", "start");
    model.result("pg1").feature("str1").set("pointtype", "arrow");
    model.result("pg1").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg1").feature("str1").set("color", "gray");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").label("\u7535\u89e3\u8d28\u7535\u6d41\u5bc6\u5ea6 (cd)");
    model.result("pg2").create("str1", "Streamline");
    model.result("pg2").feature("str1").set("expr", new String[]{"cd.Ilx", "cd.Ily", "cd.Ilz"});
    model.result("pg2").feature("str1").set("posmethod", "start");
    model.result("pg2").feature("str1").set("pointtype", "arrow");
    model.result("pg2").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg2").feature("str1").set("color", "gray");
    model.result("pg2").feature("str1").create("col1", "Color");
    model.result("pg2").feature("str1").feature("col1").set("expr", "root.comp1.cd.IlMag");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"abs(cd.itot)"});
    model.result("pg2").feature("surf1").set("inheritplot", "str1");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").label("\u5bf9\u5730\u7535\u6781\u7535\u4f4d (cd)");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"cd.phisext"});
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").label("\u7535\u6781\u7535\u4f4d vs. \u76f8\u90bb\u53c2\u6bd4\u7535\u4f4d (cd)");
    model.result("pg4").create("str1", "Streamline");
    model.result("pg4").feature("str1").set("expr", new String[]{"cd.Ilx", "cd.Ily", "cd.Ilz"});
    model.result("pg4").feature("str1").set("posmethod", "start");
    model.result("pg4").feature("str1").set("pointtype", "arrow");
    model.result("pg4").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg4").feature("str1").set("color", "gray");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"cd.Evsref"});
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").feature("str1").set("posmethod", "selection");
    model.result("pg2").feature("str1").selection().set(2);
    model.result("pg2").run();
    model.result("pg2").feature("str1").feature("col1").active(false);
    model.result("pg2").run();
    model.result("pg2").feature("surf1").create("sel1", "Selection");
    model.result("pg2").feature("surf1").feature("sel1").selection().named("geom1_arr1_bnd");
    model.result("pg2").run();
    model.result("pg2").set("edges", false);

    model.component("comp1").view("view1").set("transparency", false);

    model.result("pg2").run();
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").run();
    model.result("pg5").label("\u7535\u9540\u539a\u5ea6");
    model.result("pg5").selection().geom("geom1", 2);
    model.result("pg5").selection().named("geom1_arr1_bnd");
    model.result("pg5").set("edges", false);
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", "thickness_cathode");
    model.result("pg5").feature("surf1").set("unit", "\u00b5m");
    model.result("pg5").run();
    model.result("pg5").run();

    model.title("\u673a\u67b6\u4e0a\u591a\u4e2a\u90e8\u4ef6\u7684\u7535\u9540");

    model
         .description("\u5728\u5bf9\u591a\u4e2a\u90e8\u4ef6\u8fdb\u884c\u7535\u9540\u65f6\uff0c\u6211\u4eec\u901a\u5e38\u5c06\u5176\u5b89\u88c5\u5728\u7535\u9540\u69fd\u7684\u673a\u67b6\u4e0a\u3002\n\n\u63a5\u4e0b\u6765\uff0c\u4e00\u4e2a\u91cd\u8981\u7684\u65b9\u9762\u662f\uff0c\u4f7f\u673a\u67b6\u4e0a\u5b89\u88c5\u7684\u6240\u6709\u90e8\u4ef6\u5177\u6709\u5747\u5300\u7684\u9540\u5c42\u539a\u5ea6\u3002\n\n\u60a8\u53ef\u4ee5\u4f7f\u7528\u8be5\u793a\u4f8b\u6a21\u578b\u7814\u7a76\u591a\u4e2a\u51e0\u4f55\u548c\u64cd\u4f5c\u53c2\u6570\u5bf9\u673a\u67b6\u7535\u9540\u5747\u5300\u6027\u7684\u5f71\u54cd\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("electroplating_rack.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
