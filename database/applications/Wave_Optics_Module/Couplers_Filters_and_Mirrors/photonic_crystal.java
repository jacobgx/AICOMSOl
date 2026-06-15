/*
 * photonic_crystal.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:36 by COMSOL 6.3.0.290. */
public class photonic_crystal {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Wave_Optics_Module\\Couplers_Filters_and_Mirrors");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ewfd", "ElectromagneticWavesFrequencyDomain", "geom1");

    model.study().create("std1");
    model.study("std1").create("wave", "Wavelength");
    model.study("std1").feature("wave").set("ftplistmethod", "manual");
    model.study("std1").feature("wave").set("solnum", "auto");
    model.study("std1").feature("wave").set("notsolnum", "auto");
    model.study("std1").feature("wave").set("outputmap", new String[]{});
    model.study("std1").feature("wave").set("ngenAUX", "1");
    model.study("std1").feature("wave").set("goalngenAUX", "1");
    model.study("std1").feature("wave").set("ngenAUX", "1");
    model.study("std1").feature("wave").set("goalngenAUX", "1");
    model.study("std1").feature("wave").setSolveFor("/physics/ewfd", true);

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "photonic_crystal.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "\u6298\u5c04\u7387");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").func().create("an1", "Analytic");
    model.component("comp1").material("mat1").label("GaAs (Gallium arsenide) (Skauli et al. 2003: n 0.97-17 um)");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").func("an1")
         .set("expr", "sqrt((5.372514)+(5.466742)*x^2/(x^2-(0.19636481728249))+(0.02429960)*x^2/(x^2-(0.76500440081209))+(1.957522)*x^2/(x^2-(1362.8353555600002)))");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").func("an1").set("fununit", "1");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").func("an1")
         .set("argunit", new String[]{"um"});
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"an1(c_const/freq)", "0", "0", "0", "an1(c_const/freq)", "0", "0", "0", "an1(c_const/freq)"});
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").addInput("frequency");
    model.component("comp1").material("mat1").selection()
         .set(1, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86);
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u7a7a\u6c14");
    model.component("comp1").material("mat2").selection().set(2);
    model.component("comp1").material("mat2").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").set("n", new String[]{"1"});

    model.component("comp1").physics("ewfd").prop("components").set("components", "outofplane");
    model.component("comp1").physics("ewfd").create("sctr1", "Scattering", 1);
    model.component("comp1").physics("ewfd").feature("sctr1").selection().all();
    model.component("comp1").physics("ewfd").create("sctr2", "Scattering", 1);
    model.component("comp1").physics("ewfd").feature("sctr2").selection().set(5);
    model.component("comp1").physics("ewfd").feature("sctr2").set("IncidentField", "EField");
    model.component("comp1").physics("ewfd").feature("sctr2").set("E0i", new int[]{0, 0, 1});

    model.study("std1").feature("wave").set("plist", "1[um] 1.3[um]");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u7535\u573a (ewfd)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("expr", "ewfd.Ez");
    model.result("pg1").feature("surf1").set("descr", "\u7535\u573a\uff0cz \u5206\u91cf");
    model.result("pg1").feature("surf1").set("colortable", "WaveLight");
    model.result("pg1").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg1").run();
    model.result().dataset().create("cln1", "CutLine2D");
    model.result().dataset("cln1").setIndex("genpoints", "0.75e-6", 0, 1);
    model.result().dataset("cln1").setIndex("genpoints", "2.5e-6", 1, 0);
    model.result().dataset("cln1").setIndex("genpoints", "0.75e-6", 1, 1);
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").set("data", "cln1");
    model.result("pg2").create("lngr1", "LineGraph");
    model.result("pg2").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg2").feature("lngr1").set("linewidth", "preference");
    model.result("pg2").run();

    model.title("\u5149\u5b50\u6676\u4f53");

    model
         .description("\u901a\u8fc7\u79fb\u9664\u5149\u5b50\u6676\u4f53\u7ed3\u6784\u4e2d\u7684\u4e00\u4e9b\u6676\u67f1\uff0c\u53ef\u4ee5\u4ea7\u751f\u5149\u5b50\u6ce2\u5bfc\uff0c\u6839\u636e\u6676\u67f1\u95f4\u8ddd\u53ef\u4ee5\u5f97\u5230\u5149\u5b50\u5e26\u9699\u3002\u5728\u8fd9\u4e00\u5149\u5b50\u5e26\u9699\u5185\uff0c\u53ea\u6709\u7279\u5b9a\u9891\u7387\u8303\u56f4\u5185\u7684\u6ce2\u624d\u80fd\u901a\u8fc7\u672c\u4f8b\u4e2d\u7684\u6ce2\u5bfc\u51e0\u4f55\u4f20\u64ad\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("photonic_crystal.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
