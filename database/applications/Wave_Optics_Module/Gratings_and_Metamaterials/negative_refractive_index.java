/*
 * negative_refractive_index.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:39 by COMSOL 6.3.0.290. */
public class negative_refractive_index {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Wave_Optics_Module\\Gratings_and_Metamaterials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ewfd", "ElectromagneticWavesFrequencyDomain", "geom1");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").set("ftplistmethod", "manual");
    model.study("std1").feature("freq").set("solnum", "auto");
    model.study("std1").feature("freq").set("notsolnum", "auto");
    model.study("std1").feature("freq").set("outputmap", new String[]{});
    model.study("std1").feature("freq").set("ngenAUX", "1");
    model.study("std1").feature("freq").set("goalngenAUX", "1");
    model.study("std1").feature("freq").set("ngenAUX", "1");
    model.study("std1").feature("freq").set("goalngenAUX", "1");
    model.study("std1").feature("freq").setSolveFor("/physics/ewfd", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("lda0", "1[m]", "\u6ce2\u957f\uff0c\u771f\u7a7a");
    model.param().set("f0", "c_const/lda0", "\u9891\u7387");
    model.param().set("e_a", "1", "\u76f8\u5bf9\u4ecb\u7535\u5e38\u6570\uff0c\u771f\u7a7a");
    model.param().set("mu_a", "1", "\u76f8\u5bf9\u78c1\u5bfc\u7387\uff0c\u771f\u7a7a");
    model.param().set("e_b", "-1", "\u76f8\u5bf9\u4ecb\u7535\u5e38\u6570\uff0c\u8d85\u6750\u6599");
    model.param().set("mu_b", "-1", "\u76f8\u5bf9\u78c1\u5bfc\u7387\uff0c\u8d85\u6750\u6599");
    model.param().set("n_a", "sqrt(e_a*mu_a)", "\u6298\u5c04\u7387\uff0c\u771f\u7a7a");
    model.param().set("n_b", "-sqrt(e_b*mu_b)", "\u6298\u5c04\u7387\uff0c\u8d85\u6750\u6599");
    model.param().set("alpha", "30[deg]", "\u5165\u5c04\u89d2");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").label("\u53cc\u7aef\u53e3\u6a21\u578b");
    model.component("comp1").geom("geom1").feature("r1").set("size", new int[]{1, 3});
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", 1.5, 0);
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").label("PML \u6a21\u578b");
    model.component("comp1").geom("geom1").feature("r2").set("size", new int[]{1, 4});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new double[]{1.05, -1});
    model.component("comp1").geom("geom1").feature("r2").setIndex("layer", 1, 0);
    model.component("comp1").geom("geom1").feature("r2").setIndex("layername", "\u5c42 2", 0);
    model.component("comp1").geom("geom1").feature("r2").setIndex("layer", 1.5, 1);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("ewfd").prop("components").set("components", "outofplane");
    model.component("comp1").physics("ewfd").feature("wee1").set("DisplacementFieldModel", "RelativePermittivity");
    model.component("comp1").physics("ewfd").create("port1", "Port", 1);
    model.component("comp1").physics("ewfd").feature("port1").selection().set(5);
    model.component("comp1").physics("ewfd").feature("port1").set("PortType", "Periodic");
    model.component("comp1").physics("ewfd").feature("port1").set("alpha_inc", "alpha");
    model.component("comp1").physics("ewfd").feature("port1").set("n_mat", "UserDefined");
    model.component("comp1").physics("ewfd").feature("port1").set("n", "n_a");
    model.component("comp1").physics("ewfd").create("port2", "Port", 1);
    model.component("comp1").physics("ewfd").feature("port2").selection().set(2);
    model.component("comp1").physics("ewfd").feature("port2").set("PortType", "Periodic");
    model.component("comp1").physics("ewfd").feature("port2").set("n_mat", "UserDefined");
    model.component("comp1").physics("ewfd").feature("port2").set("n", "n_b");
    model.component("comp1").physics("ewfd").create("pc1", "PeriodicCondition", 1);
    model.component("comp1").physics("ewfd").feature("pc1").selection().set(1, 3, 6, 7);
    model.component("comp1").physics("ewfd").feature("pc1").set("PeriodicType", "Floquet");
    model.component("comp1").physics("ewfd").feature("pc1").set("Floquet_source", "FromPeriodicPort");
    model.component("comp1").physics("ewfd").create("port3", "Port", 1);
    model.component("comp1").physics("ewfd").feature("port3").selection().set(14);
    model.component("comp1").physics("ewfd").feature("port3").set("PortExcitation", "on");
    model.component("comp1").physics("ewfd").feature("port3").set("PortType", "Periodic");
    model.component("comp1").physics("ewfd").feature("port3").set("alpha_inc", "alpha");
    model.component("comp1").physics("ewfd").feature("port3").set("n_mat", "UserDefined");
    model.component("comp1").physics("ewfd").feature("port3").set("n", "n_a");
    model.component("comp1").physics("ewfd").create("pc2", "PeriodicCondition", 1);
    model.component("comp1").physics("ewfd").feature("pc2").selection().set(8, 10, 12, 15, 16, 17);
    model.component("comp1").physics("ewfd").feature("pc2").set("PeriodicType", "Floquet");
    model.component("comp1").physics("ewfd").feature("pc2").set("Floquet_source", "FromPeriodicPort");

    model.component("comp1").coordSystem().create("pml1", "PML");
    model.component("comp1").coordSystem("pml1").selection().set(3);
    model.component("comp1").coordSystem("pml1").set("PMLfactor", "-1");

    model.component("comp1").physics("ewfd").create("trans1", "TransitionBoundaryCondition", 1);
    model.component("comp1").physics("ewfd").feature("trans1").selection().set(4, 13);
    model.component("comp1").physics("ewfd").feature("trans1").set("DisplacementFieldModel", "RelativePermittivity");
    model.component("comp1").physics("ewfd").feature("trans1").set("murbnd_mat", "userdef");
    model.component("comp1").physics("ewfd").feature("trans1").set("murbnd", "mu_a");
    model.component("comp1").physics("ewfd").feature("trans1").set("epsilonr_mat", "userdef");
    model.component("comp1").physics("ewfd").feature("trans1").set("epsilonr", "e_a");
    model.component("comp1").physics("ewfd").feature("trans1").set("sigmabnd_mat", "userdef");
    model.component("comp1").physics("ewfd").feature("trans1").set("d", "lda0/1000");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").selection().set(2, 5);
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermittivity", new String[]{"e_a"});
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermeability", new String[]{"mu_a"});
    model.component("comp1").material("mat1").propertyGroup("def").set("electricconductivity", new String[]{"0"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").selection().set(1, 3, 4);
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermittivity", new String[]{"e_b"});
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermeability", new String[]{"mu_b"});
    model.component("comp1").material("mat2").propertyGroup("def").set("electricconductivity", new String[]{"0"});

    model.study("std1").feature("freq").set("plist", "f0");

    model.component("comp1").mesh("mesh1").run();

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u7535\u573a (ewfd)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").run();
    model.result().dataset("dset1").selection().geom("geom1", 2);
    model.result().dataset("dset1").selection().geom("geom1", 2);
    model.result().dataset("dset1").selection().set(1, 2, 4, 5);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("expr", "Ez");
    model.result("pg1").feature("surf1").set("colortable", "WaveLight");
    model.result("pg1").run();
    model.result("pg1").create("con1", "Contour");
    model.result("pg1").feature("con1").set("expr", "Ez");
    model.result("pg1").feature("con1").set("number", 12);
    model.result("pg1").feature("con1").set("colortable", "GrayPrint");

    model.title("\u8d1f\u6298\u5c04\u7387\u5efa\u6a21");

    model
         .description("\u5728\u8bbe\u8ba1\u5468\u671f\u6027\u7ed3\u6784\u65f6\u91c7\u7528\u5c3a\u5ea6\u4e0a\u53ef\u4e0e\u6ce2\u957f\u76f8\u6bd4\u62df\u7684\u7279\u5f81\uff0c\u53ef\u4ee5\u83b7\u5f97\u4ecb\u7535\u5e38\u6570\u548c\u78c1\u5bfc\u7387\u5747\u4e3a\u8d1f\u503c\u7684\u6750\u6599\u3002\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u5bf9\u672c\u4f53\u4e2d\u4ecb\u7535\u5e38\u6570\u548c\u78c1\u5bfc\u7387\u5747\u4e3a\u8d1f\u7684\u8d85\u6750\u6599\u57df\u5efa\u6a21\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("negative_refractive_index.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
