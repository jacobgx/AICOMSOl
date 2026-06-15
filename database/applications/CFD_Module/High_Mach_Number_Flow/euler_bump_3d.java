/*
 * euler_bump_3d.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:31 by COMSOL 6.3.0.290. */
public class euler_bump_3d {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\CFD_Module\\High_Mach_Number_Flow");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("hmnf", "HighMachNumberFlow", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").set("solnum", "auto");
    model.study("std1").feature("stat").set("notsolnum", "auto");
    model.study("std1").feature("stat").set("outputmap", new String[]{});
    model.study("std1").feature("stat").set("ngenAUX", "1");
    model.study("std1").feature("stat").set("goalngenAUX", "1");
    model.study("std1").feature("stat").set("ngenAUX", "1");
    model.study("std1").feature("stat").set("goalngenAUX", "1");
    model.study("std1").feature("stat").setSolveFor("/physics/hmnf", true);

    model.param().set("Min", "1.4");
    model.param().descr("Min", "\u5165\u53e3\u9a6c\u8d6b\u6570");
    model.param().set("pin", "1[atm]");
    model.param().descr("pin", "\u5165\u53e3\u9759\u538b");
    model.param().set("Tin", "273.15[K]");
    model.param().descr("Tin", "\u5165\u53e3\u9759\u6001\u6e29\u5ea6");
    model.param().set("Rs", "287[J/kg/K]");
    model.param().descr("Rs", "\u6bd4\u6c14\u4f53\u5e38\u6570");
    model.param().set("gamma", "1.4");
    model.param().descr("gamma", "\u6bd4\u70ed\u7387");
    model.param().set("uin", "Min*sqrt(gamma*Rs*Tin)");
    model.param().descr("uin", "\u5165\u53e3\u901f\u5ea6");
    model.param().set("alpha", "30[deg]");
    model.param().descr("alpha", "\u969c\u788d\u7269\u5939\u89d2");

    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new double[]{5, 0.5, 2});
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new int[]{-1, 0, 0});
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickplane", "xz");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("r", "(0.5^2/0.042+0.042)/2");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1")
         .set("pos", new String[]{"0.5", "0.042-(0.5^2/0.042+0.042)/2"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", -0.5, 0);
    model.component("comp1").geom("geom1").feature("ext1").setIndex("displ", "0.5*tan(alpha)", 0, 0);
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("blk1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("ext1");
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").view().create("view3", "geom1");
    model.component("comp1").view("view3").light("lgt1").set("intensity", 0.6);
    model.component("comp1").view("view3").light("lgt2").set("intensity", 0.5);
    model.component("comp1").view("view3").light("lgt3").set("intensity", 0.3);
    model.component("comp1").view("view3").set("transparency", true);
    model.component("comp1").view("view1").set("transparency", true);
    model.component("comp1").view().create("view4", "geom1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("hmnf").prop("AdvancedSettingProperty").set("UsePseudoTime", false);
    model.component("comp1").physics("hmnf").feature("fluid1").set("k_mat", "userdef");
    model.component("comp1").physics("hmnf").feature("fluid1")
         .set("k", new String[]{"1e-8", "0", "0", "0", "1e-8", "0", "0", "0", "1e-8"});
    model.component("comp1").physics("hmnf").feature("fluid1").set("Rs_mat", "userdef");
    model.component("comp1").physics("hmnf").feature("fluid1").set("Rs", "Rs");
    model.component("comp1").physics("hmnf").feature("fluid1").set("CpOrGammaOption", "gamma");
    model.component("comp1").physics("hmnf").feature("fluid1").set("gamma_mat", "userdef");
    model.component("comp1").physics("hmnf").feature("fluid1").set("gamma", "gamma");
    model.component("comp1").physics("hmnf").feature("fluid1").set("mu_mat", "userdef");
    model.component("comp1").physics("hmnf").feature("fluid1").set("mu", "1e-8");
    model.component("comp1").physics("hmnf").feature("wallbc1").set("BoundaryCondition", "Slip");
    model.component("comp1").physics("hmnf").feature("init1").set("u_init", new String[]{"uin", "0", "0"});
    model.component("comp1").physics("hmnf").feature("init1").set("p_init", "pin");
    model.component("comp1").physics("hmnf").feature("init1").set("Tinit", "Tin");
    model.component("comp1").physics("hmnf").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("hmnf").feature("sym1").selection().set(2, 5);

    model.component("comp1").view("view4").set("renderwireframe", true);
    model.component("comp1").view("view4").set("transparency", true);

    model.component("comp1").physics("hmnf").create("hminl1", "HighMachNumberFlowInlet", 2);
    model.component("comp1").physics("hmnf").feature("hminl1").selection().set(1);
    model.component("comp1").physics("hmnf").feature("hminl1").set("FlowCondition", "Supersonic");
    model.component("comp1").physics("hmnf").feature("hminl1").set("p0stat", "pin");
    model.component("comp1").physics("hmnf").feature("hminl1").set("T0stat", "Tin");
    model.component("comp1").physics("hmnf").feature("hminl1").set("Ma0", "Min");
    model.component("comp1").physics("hmnf").create("hmout1", "HighMachNumberFlowOutlet", 2);
    model.component("comp1").physics("hmnf").feature("hmout1").selection().set(9);

    model.component("comp1").view("view1").set("renderwireframe", true);
    model.component("comp1").view("view1").set("transparency", false);

    model.component("comp1").physics("hmnf").feature("hmout1").set("FlowCondition", "Supersonic");

    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").set("errestandadap", "adaption");
    model.study("std1").feature("stat").set("meshadaptmethod", "modify");
    model.study("std1").feature("stat").set("userngen", true);
    model.study("std1").feature("stat").set("ngen", 2);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s1").feature("fc1").set("linsolver", "i1");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset("dset2").set("geom", "geom1");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u901f\u5ea6 (hmnf)");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").setIndex("looplevel", 3, 0);
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("slc1", "Slice");
    model.result("pg1").feature("slc1").label("\u5207\u9762");
    model.result("pg1").feature("slc1").set("showsolutionparams", "on");
    model.result("pg1").feature("slc1").set("smooth", "internal");
    model.result("pg1").feature("slc1").set("showsolutionparams", "on");
    model.result("pg1").feature("slc1").set("data", "parent");
    model.result().dataset().create("surf1", "Surface");
    model.result().dataset("surf1").label("\u5916\u58c1");
    model.result().dataset("surf1").set("data", "dset2");
    model.result().dataset("surf1").selection().geom("geom1", 2);
    model.result().dataset("surf1").selection().set(3, 4, 6, 7, 8);
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u538b\u529b (hmnf)");
    model.result("pg2").set("data", "surf1");
    model.result("pg2").setIndex("looplevel", 3, 0);
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").label("\u8868\u9762");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("expr", "p");
    model.result("pg2").feature("surf1").set("colortable", "Dipole");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg2").feature("surf1").feature().create("tran1", "Transparency");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u6e29\u5ea6 (hmnf)");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").setIndex("looplevel", 3, 0);
    model.result("pg3").feature().create("vol1", "Volume");
    model.result("pg3").feature("vol1").set("showsolutionparams", "on");
    model.result("pg3").feature("vol1").set("solutionparams", "parent");
    model.result("pg3").feature("vol1").set("expr", "T");
    model.result("pg3").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg3").feature("vol1").set("smooth", "internal");
    model.result("pg3").feature("vol1").set("showsolutionparams", "on");
    model.result("pg3").feature("vol1").set("data", "parent");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("\u9a6c\u8d6b\u6570 (hmnf)");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").setIndex("looplevel", 3, 0);
    model.result("pg4").feature().create("slc1", "Slice");
    model.result("pg4").feature("slc1").label("\u5207\u9762");
    model.result("pg4").feature("slc1").set("expr", "hmnf.Ma");
    model.result("pg4").feature("slc1").set("smooth", "internal");
    model.result("pg4").feature("slc1").set("data", "parent");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").set("data", "dset2");
    model.result("pg2").set("edges", false);
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("coloring", "uniform");
    model.result("pg2").feature("surf1").set("color", "gray");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").feature("tran1").active(false);
    model.result("pg2").run();
    model.result("pg2").feature("surf1").create("sel1", "Selection");
    model.result("pg2").feature("surf1").feature("sel1").selection().set(3, 5, 6, 7, 8);
    model.result("pg2").run();
    model.result("pg2").set("view", "view3");
    model.result("pg2").create("con1", "Contour");
    model.result("pg2").feature("con1").set("expr", "p");
    model.result("pg2").feature("con1").set("number", 40);
    model.result("pg2").run();
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").feature().remove("slc1");
    model.result("pg4").run();
    model.result("pg4").set("view", "view4");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "hmnf.Ma");
    model.result("pg4").run();
    model.result("pg2").run();

    model.title("\u542b\u51f8\u8d77\u6d41\u9053\u4e2d\u7684\u8d85\u97f3\u901f\u6d41\u4e09\u7ef4\u6a21\u578b");

    model
         .description("\u4e09\u7ef4\u8d85\u97f3\u901f\u6d41\u4ee5 1.4\u00a0\u9a6c\u8d6b\u7684\u901f\u5ea6\u8fdb\u5165\u901a\u9053\uff0c\u6d41\u4f53\u649e\u51fb\u901a\u9053\u5e95\u90e8\u7684\u969c\u788d\u7269\uff0c\u4ea7\u751f\u6fc0\u6ce2\uff1b\u6fc0\u6ce2\u5728\u6d41\u4f53\u4e2d\u4f20\u64ad\uff0c\u5e76\u5728\u901a\u9053\u58c1\u53d1\u751f\u884d\u5c04\u3002\u6d41\u52a8\u5047\u8bbe\u4e3a\u53ef\u538b\u7f29\u65e0\u9ecf\u6d41\u3002\n\n\u8be5\u6a21\u578b\u91c7\u7528 COMSOL Multiphysics \u4e2d\u7684\u81ea\u9002\u5e94\u7f51\u683c\u7ec6\u5316\u7279\u5f81\u3002\u672c\u4f8b\u662f\u5728\u4e00\u4e2a\u4e8c\u7ef4\u6848\u4f8b\u7684\u57fa\u7840\u4e0a\u521b\u5efa\u7684\uff0c\u540e\u8005\u5728\u65e9\u671f\u7684\u65e0\u9ecf\u53ef\u538b\u7f29\u6d41\u52a8\u7814\u7a76\u4e2d\u88ab\u5e7f\u6cdb\u4f7f\u7528\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();

    model.label("euler_bump_3d.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
