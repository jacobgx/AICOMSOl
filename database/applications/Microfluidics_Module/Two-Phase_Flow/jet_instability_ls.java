/*
 * jet_instability_ls.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:12 by COMSOL 6.3.0.290. */
public class jet_instability_ls {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Microfluidics_Module\\Two-Phase_Flow");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");
    model.component("comp1").physics().create("ls", "LevelSet", "geom1");

    model.component("comp1").multiphysics().create("tpf1", "TwoPhaseFlowLevelSet", 2);
    model.component("comp1").multiphysics("tpf1").set("Fluid_physics", "spf");
    model.component("comp1").multiphysics("tpf1").set("Mathematics_physics", "ls");
    model.component("comp1").multiphysics("tpf1").selection().all();
    model.component("comp1").multiphysics().create("ww1", "WettedWall", 1);
    model.component("comp1").multiphysics("ww1").set("Fluid_physics", "spf");
    model.component("comp1").multiphysics("ww1").set("Mathematics_physics", "ls");

    model.study().create("std1");
    model.study("std1").create("phasei", "PhaseInitialization");
    model.study("std1").feature("phasei").set("ftplistmethod", "manual");
    model.study("std1").feature("phasei").set("solnum", "auto");
    model.study("std1").feature("phasei").set("notsolnum", "auto");
    model.study("std1").feature("phasei").set("outputmap", new String[]{});
    model.study("std1").feature("phasei").set("ngenAUX", "1");
    model.study("std1").feature("phasei").set("goalngenAUX", "1");
    model.study("std1").feature("phasei").set("ngenAUX", "1");
    model.study("std1").feature("phasei").set("goalngenAUX", "1");
    model.study("std1").feature("phasei").setSolveFor("/physics/spf", true);
    model.study("std1").feature("phasei").setSolveFor("/physics/ls", true);
    model.study("std1").feature("phasei").setSolveFor("/multiphysics/tpf1", true);
    model.study("std1").feature("phasei").setSolveFor("/physics/spf", false);
    model.study("std1").feature("phasei").setSolveFor("/multiphysics/ww1", true);
    model.study("std1").feature("phasei").setSolveFor("/physics/spf", false);
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").set("initstudy", "std1");
    model.study("std1").feature("time").set("notstudy", "std1");
    model.study("std1").feature("time").set("ftplistmethod", "manual");
    model.study("std1").feature("time").set("initialtime", "0");
    model.study("std1").feature("time").set("useinitsol", "on");
    model.study("std1").feature("time").set("notsolmethod", "sol");
    model.study("std1").feature("time").set("outputmap", new String[]{});
    model.study("std1").feature("time").setSolveFor("/physics/spf", true);
    model.study("std1").feature("time").setSolveFor("/physics/ls", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/tpf1", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/ww1", true);

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new int[]{5, 60});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new int[]{0, -30});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new int[]{15, 60});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new int[]{0, -30});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", 15, 0);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"1000"});
    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", new String[]{"1e-3"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").selection().set(2);
    model.component("comp1").material("mat2").propertyGroup("def").set("density", new String[]{"1.225"});
    model.component("comp1").material("mat2").propertyGroup("def").set("dynamicviscosity", new String[]{"1.789e-5"});

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("sigma", "0.07*(1-0.2*cos(2*pi*z/60[um]))");
    model.component("comp1").variable("var1").descr("sigma", "\u8868\u9762\u5f20\u529b\u8868\u8fbe\u5f0f");

    model.component("comp1").physics("spf").prop("ShapeProperty").set("order_fluid", 4);
    model.component("comp1").physics("ls").prop("ShapeProperty").set("order_levelsetvariable", 2);

    model.component("comp1").multiphysics("tpf1").set("Fluid1", "mat1");
    model.component("comp1").multiphysics("tpf1").set("Fluid2", "mat2");
    model.component("comp1").multiphysics("tpf1").set("IncludeSurfaceTension", true);
    model.component("comp1").multiphysics("tpf1").set("SurfaceTensionCoefficient", "userdef");
    model.component("comp1").multiphysics("tpf1").set("sigma", "sigma");

    model.component("comp1").physics("spf").create("prpc1", "PressurePointConstraint", 0);
    model.component("comp1").physics("spf").feature("prpc1").selection().set(6);
    model.component("comp1").physics("ls").feature("initfluid2").selection().set(2);
    model.component("comp1").physics("spf").create("sym1", "Symmetry", 1);
    model.component("comp1").physics("spf").feature("sym1").selection().set(2, 3, 5, 6);
    model.component("comp1").physics("ls").create("sym1", "SymmetryFluid", 1);
    model.component("comp1").physics("ls").feature("sym1").selection().set(2, 3, 5, 6);

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("size").set("table", "cfd");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tlist", "range(0,2e-7,2e-5)");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u901f\u5ea6 (spf)");
    model.result("pg1").set("dataisaxisym", "off");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u538b\u529b (spf)");
    model.result("pg2").set("dataisaxisym", "off");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("con1", "Contour");
    model.result("pg2").feature("con1").label("\u7b49\u503c\u7ebf");
    model.result("pg2").feature("con1").set("showsolutionparams", "on");
    model.result("pg2").feature("con1").set("expr", "p");
    model.result("pg2").feature("con1").set("number", 40);
    model.result("pg2").feature("con1").set("levelrounding", false);
    model.result("pg2").feature("con1").set("smooth", "internal");
    model.result("pg2").feature("con1").set("showsolutionparams", "on");
    model.result("pg2").feature("con1").set("data", "parent");
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").label("\u4e8c\u7ef4\u65cb\u8f6c");
    model.result().dataset("rev1").set("data", "none");
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("data", "dset1");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u4e09\u7ef4\u901f\u5ea6 (spf)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").label("\u8868\u9762");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("\u6d41\u4f53 1 \u7684\u4f53\u79ef\u5206\u6570 (ls)");
    model.result("pg4").set("dataisaxisym", "off");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "ls.Vf1");
    model.result("pg4").feature("surf1").set("smooth", "internal");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result("pg4").feature().create("con1", "Contour");
    model.result("pg4").feature("con1").set("expr", "ls.Vf1");
    model.result("pg4").feature("con1").set("levelmethod", "levels");
    model.result("pg4").feature("con1").set("levels", "0.5");
    model.result("pg4").feature("con1").set("coloring", "uniform");
    model.result("pg4").feature("con1").set("colorlegend", false);
    model.result("pg4").feature("con1").set("color", "gray");
    model.result("pg4").feature("con1").set("smooth", "none");
    model.result("pg4").feature("con1").set("data", "parent");
    model.result().dataset().create("rev2", "Revolve2D");
    model.result().dataset("rev2").label("\u4e8c\u7ef4\u65cb\u8f6c 1");
    model.result().dataset("rev2").set("data", "dset1");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").label("\u6d41\u4f53 1 \u7684\u4f53\u79ef\u5206\u6570 (ls) 1");
    model.result("pg5").set("data", "rev2");
    model.result("pg5").setIndex("looplevel", 101, 0);
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").feature().create("iso1", "Isosurface");
    model.result("pg5").feature("iso1").set("expr", "ls.Vf1");
    model.result("pg5").feature("iso1").set("levelmethod", "levels");
    model.result("pg5").feature("iso1").set("levels", "0.5");
    model.result("pg5").feature("iso1").set("coloring", "uniform");
    model.result("pg5").feature("iso1").set("color", "gray");
    model.result("pg5").feature("iso1").set("smooth", "none");
    model.result("pg5").feature("iso1").set("data", "parent");
    model.result("pg1").run();
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").run();
    model.result("pg6").set("edges", false);
    model.result("pg6").create("con1", "Contour");
    model.result("pg6").feature("con1").set("expr", "ls.Vf1");
    model.result("pg6").feature("con1").set("levelmethod", "levels");
    model.result("pg6").feature("con1").set("levels", 0.5);
    model.result("pg6").feature("con1").set("contourtype", "filled");
    model.result("pg6").feature("con1").set("colortable", "GrayScale");
    model.result("pg6").feature("con1").set("colortabletrans", "reverse");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").label("\u6d41\u4f53 1 \u7684\u4f53\u79ef\u5206\u6570");
    model.result("pg6").setIndex("looplevel", 1, 0);
    model.result("pg6").run();
    model.result("pg6").setIndex("looplevel", 31, 0);
    model.result("pg6").run();
    model.result("pg6").setIndex("looplevel", 46, 0);
    model.result("pg6").run();
    model.result("pg6").setIndex("looplevel", 51, 0);
    model.result("pg6").run();
    model.result("pg6").setIndex("looplevel", 56, 0);
    model.result("pg6").run();
    model.result("pg6").setIndex("looplevel", 83, 0);
    model.result("pg6").run();
    model.result("pg5").run();
    model.result("pg5").setIndex("looplevel", 81, 0);
    model.result("pg5").run();

    model.title("\u5c04\u6d41\u4e0d\u7a33\u5b9a\u6027 - \u6c34\u5e73\u96c6");

    model
         .description("\u5f53\u8868\u9762\u5f20\u529b\u7cfb\u6570\u5b58\u5728\u68af\u5ea6\u65f6\uff0c\u9a6c\u5170\u6208\u5c3c\u6548\u5e94\u4f1a\u5728\u6d41\u4f53/\u6d41\u4f53\u754c\u9762\u7684\u5207\u5411\u4ea7\u751f\u6ed1\u79fb\u901f\u5ea6\u3002\u5728\u672c\u4f8b\u4e2d\uff0c\u7531\u4e8e\u5b58\u5728\u5468\u671f\u6027\u7684\u8868\u9762\u5f20\u529b\u68af\u5ea6\uff0c\u8f83\u957f\u7684\u5c04\u6d41\u5728\u60ef\u6027\u53c2\u8003\u7cfb\u4e2d\u7834\u788e\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("jet_instability_ls.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
