/*
 * blood_vessel.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:08 by COMSOL 6.3.0.290. */
public class blood_vessel {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Fluid-Structure_Interaction");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");
    model.component("comp1").physics("spf").prop("AdvancedSettingProperty").set("UsePseudoTime", "1");
    model.component("comp1").physics("spf").prop("PhysicalModelProperty").set("Compressibility", "Incompressible");
    model.component("comp1").physics("spf").field("velocity").field("u_fluid");
    model.component("comp1").physics("spf").field("velocity")
         .component(new String[]{"u_fluid", "v_fluid", "w_fluid"});
    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");
    model.component("comp1").physics("solid").field("displacement").field("u_solid");
    model.component("comp1").physics("solid").field("displacement")
         .component(new String[]{"u_solid", "v_solid", "w_solid"});
    model.component("comp1").physics("solid").prop("ShapeProperty").set("order_displacement", "2");

    model.component("comp1").multiphysics().create("fsi1", "FluidStructureInteractionBC", 2);
    model.component("comp1").multiphysics("fsi1").set("Fluid_physics", "spf");
    model.component("comp1").multiphysics("fsi1").set("Structure_physics", "solid");
    model.component("comp1").multiphysics("fsi1").selection().all();

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/spf", true);
    model.study("std1").feature("time").setSolveFor("/physics/solid", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/fsi1", true);

    model.param().set("t", "0[s]");
    model.param().descr("t", "\u65f6\u95f4\u8fde\u7eed\u53c2\u6570");
    model.param().set("alpha", "1/3");
    model.param().descr("alpha", "\u5fc3\u810f\u8df3\u52a8\u65f6\u7684\u76f8\u5bf9\u538b\u529b\u5e45\u503c");

    model.func().create("pw1", "Piecewise");
    model.func("pw1").set("funcname", "f");
    model.func("pw1").set("arg", "t");
    model.func("pw1").setIndex("pieces", 0, 0, 0);
    model.func("pw1").setIndex("pieces", 0.5, 0, 1);
    model.func("pw1").setIndex("pieces", "(1-alpha)*sin(pi*t)", 0, 2);
    model.func("pw1").setIndex("pieces", 0.5, 1, 0);
    model.func("pw1").setIndex("pieces", 1.5, 1, 1);
    model.func("pw1").setIndex("pieces", "1-alpha*cos(2*pi*(t-0.5))", 1, 2);
    model.func("pw1").set("argunit", "s");
    model.func("pw1").set("fununit", "1");

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "blood_vessel.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").create("sca1", "Scale");
    model.component("comp1").geom("geom1").feature("sca1").set("isotropic", 0.01);
    model.component("comp1").geom("geom1").feature("sca1").selection("input").set("imp1");
    model.component("comp1").geom("geom1").run();
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").feature().create("rmd1", "RemoveDetails");
    model.component("comp1").geom("geom1").feature("rmd1").set("detailsizetype", "absolute");
    model.component("comp1").geom("geom1").feature("rmd1").set("maxabssize", "1E-4");
    model.component("comp1").geom("geom1").run("rmd1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view1").set("transparency", true);

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u8840\u6db2");
    model.component("comp1").selection("sel1").set(3);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u52a8\u8109");
    model.component("comp1").selection("sel2").set(2);
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u5fc3\u808c");
    model.component("comp1").selection("sel3").set(1);
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").label("\u5165\u53e3");
    model.component("comp1").selection("sel4").geom(2);
    model.component("comp1").selection("sel4").set(38);
    model.component("comp1").selection().create("sel5", "Explicit");
    model.component("comp1").selection("sel5").label("\u51fa\u53e3 1");
    model.component("comp1").selection("sel5").geom(2);
    model.component("comp1").selection("sel5").set(19);
    model.component("comp1").selection().create("sel6", "Explicit");
    model.component("comp1").selection("sel6").label("\u51fa\u53e3 2");
    model.component("comp1").selection("sel6").geom(2);
    model.component("comp1").selection("sel6").set(9);
    model.component("comp1").selection().create("sel7", "Explicit");
    model.component("comp1").selection("sel7").label("\u51fa\u53e3 3");
    model.component("comp1").selection("sel7").geom(2);
    model.component("comp1").selection("sel7").set(41);
    model.component("comp1").selection().create("sel8", "Explicit");
    model.component("comp1").selection("sel8").label("\u51fa\u53e3 4");
    model.component("comp1").selection("sel8").geom(2);
    model.component("comp1").selection("sel8").set(70);
    model.component("comp1").selection().create("sel9", "Explicit");
    model.component("comp1").selection("sel9").label("\u51fa\u53e3 5");
    model.component("comp1").selection("sel9").geom(2);
    model.component("comp1").selection("sel9").set(84);
    model.component("comp1").selection().create("sel10", "Explicit");
    model.component("comp1").selection("sel10").label("\u8f8a\u652f\u627f\u8fb9\u754c");
    model.component("comp1").selection("sel10").geom(2);
    model.component("comp1").selection("sel10").set(1, 2, 3, 4, 5, 6, 12, 26, 27, 30, 33, 64, 67, 83, 85);
    model.component("comp1").selection().create("sel11", "Explicit");
    model.component("comp1").selection("sel11").label("\u8d1f\u8f7d\u8fb9\u754c");
    model.component("comp1").selection("sel11").geom(2);
    model.component("comp1").selection("sel11").set(10, 20, 36, 42, 68);
    model.component("comp1").selection("sel11").set("groupcontang", true);

    model.component("comp1").physics("spf").selection().named("sel1");
    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 2);
    model.component("comp1").physics("spf").feature("inl1").selection().named("sel4");
    model.component("comp1").physics("spf").feature("inl1").set("BoundaryCondition", "Pressure");
    model.component("comp1").physics("spf").feature("inl1").set("p0", "126.09[mmHg]*f(t)");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 2);
    model.component("comp1").physics("spf").feature("out1").selection().named("sel5");
    model.component("comp1").physics("spf").feature("out1").set("p0", "125.91[mmHg]*f(t)");
    model.component("comp1").physics("spf").create("out2", "OutletBoundary", 2);
    model.component("comp1").physics("spf").feature("out2").selection().named("sel6");
    model.component("comp1").physics("spf").feature("out2").set("p0", "125.415[mmHg]*f(t)");
    model.component("comp1").physics("spf").create("out3", "OutletBoundary", 2);
    model.component("comp1").physics("spf").feature("out3").selection().named("sel7");
    model.component("comp1").physics("spf").feature("out3").set("p0", "125.415[mmHg]*f(t)");
    model.component("comp1").physics("spf").create("out4", "OutletBoundary", 2);
    model.component("comp1").physics("spf").feature("out4").selection().named("sel8");
    model.component("comp1").physics("spf").feature("out4").set("p0", "125.415[mmHg]*f(t)");
    model.component("comp1").physics("spf").create("out5", "OutletBoundary", 2);
    model.component("comp1").physics("spf").feature("out5").selection().named("sel9");
    model.component("comp1").physics("spf").feature("out5").set("p0", "125.1[mmHg]*f(t)");
    model.component("comp1").physics("solid").selection().set(1, 2);
    model.component("comp1").physics("solid").feature("lemm1").set("IsotropicOption", "Lame");
    model.component("comp1").physics("solid").feature("lemm1").set("MixedFormulation", "pFormulation");
    model.component("comp1").physics("solid").create("roll1", "Roller", 2);
    model.component("comp1").physics("solid").feature("roll1").selection().named("sel10");

    model.component("comp1").multiphysics("fsi1").set("CouplingType", "FluidLoading");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u8840\u6db2");
    model.component("comp1").material("mat1").selection().named("sel1");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"1060"});
    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", new String[]{"0.005"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u52a8\u8109");
    model.component("comp1").material("mat2").selection().named("sel2");
    model.component("comp1").material("mat2").propertyGroup().create("Lame", "Lame", "Lame_parameters");
    model.component("comp1").material("mat2").propertyGroup("Lame").set("lambLame", new String[]{"20*muLame"});
    model.component("comp1").material("mat2").propertyGroup("Lame").set("muLame", new String[]{"6.20e6"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", new String[]{"960"});
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").label("\u5fc3\u808c");
    model.component("comp1").material("mat3").selection().named("sel3");
    model.component("comp1").material("mat3").propertyGroup().create("Lame", "Lame", "Lame_parameters");
    model.component("comp1").material("mat3").propertyGroup("Lame").set("lambLame", new String[]{"20*muLame"});
    model.component("comp1").material("mat3").propertyGroup("Lame").set("muLame", new String[]{"7.20e6"});
    model.component("comp1").material("mat3").propertyGroup("def").set("density", new String[]{"1200"});

    model.component("comp1").mesh("mesh1").autoMeshSize(4);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tlist", "range(0,0.05,1.5)");
    model.study("std1").feature("time").setSolveFor("/physics/solid", false);
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/spf", false);
    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "t", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "s", 0);
    model.study("std1").feature("stat").setIndex("pname", "t", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "s", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(0,0.05,1.5)", 0);
    model.study("std1").feature("stat").set("usesol", true);
    model.study("std1").feature("stat").set("notsolmethod", "sol");
    model.study("std1").feature("stat").set("notstudy", "std1");
    model.study("std1").feature("stat").set("notsolnum", "all");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("tstepsbdf", "intermediate");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u901f\u5ea6 (spf)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("slc1", "Slice");
    model.result("pg1").feature("slc1").label("\u5207\u9762");
    model.result("pg1").feature("slc1").set("showsolutionparams", "on");
    model.result("pg1").feature("slc1").set("smooth", "internal");
    model.result("pg1").feature("slc1").set("showsolutionparams", "on");
    model.result("pg1").feature("slc1").set("data", "parent");
    model.result().dataset().create("surf1", "Surface");
    model.result().dataset("surf1").label("\u5916\u58c1");
    model.result().dataset("surf1").set("data", "dset1");
    model.result().dataset("surf1").selection().geom("geom1", 2);
    model.result().dataset("surf1").selection()
         .set(10, 11, 16, 17, 20, 21, 23, 24, 36, 37, 39, 40, 42, 43, 45, 46, 50, 51, 52, 53, 58, 59, 61, 62, 68, 69, 71, 72, 77, 78, 80, 81);
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u538b\u529b (spf)");
    model.result("pg2").set("data", "surf1");
    model.result("pg2").setIndex("looplevel", 31, 0);
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
    model.result("pg3").set("data", "dset1");
    model.result("pg3").setIndex("looplevel", 31, 0);
    model.result("pg3").label("\u5e94\u529b (solid)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").create("vol1", "Volume");
    model.result("pg3").feature("vol1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg3").feature("vol1").set("threshold", "manual");
    model.result("pg3").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg3").feature("vol1").set("colortable", "Rainbow");
    model.result("pg3").feature("vol1").set("colortabletrans", "none");
    model.result("pg3").feature("vol1").set("colorscalemode", "linear");
    model.result("pg3").feature("vol1").set("resolution", "custom");
    model.result("pg3").feature("vol1").set("refine", 2);
    model.result("pg3").feature("vol1").set("colortable", "Prism");
    model.result("pg3").feature("vol1").create("def", "Deform");
    model.result("pg3").feature("vol1").feature("def").set("expr", new String[]{"u_solid", "v_solid", "w_solid"});
    model.result("pg3").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg1").run();

    model.component("comp1").view("view1").set("transparency", false);

    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 21, 0);
    model.result("pg1").run();
    model.result("pg1").feature("slc1").set("quickplane", "zx");
    model.result("pg1").feature("slc1").set("quickynumber", 1);
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("unit", "mmHg");
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").selection().geom("geom1", 3);
    model.result("pg3").selection().named("sel2");
    model.result("pg3").run();
    model.result("pg3").feature("vol1").feature("def").set("scaleactive", true);
    model.result("pg3").feature("vol1").feature("def").set("scale", 300);
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").setIndex("looplevel", 31, 0);
    model.result("pg4").label("\u4f4d\u79fb (solid)");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").set("showlegends", true);
    model.result("pg4").create("vol1", "Volume");
    model.result("pg4").feature("vol1").set("expr", new String[]{"solid.disp"});
    model.result("pg4").feature("vol1").set("threshold", "manual");
    model.result("pg4").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg4").feature("vol1").set("colortable", "SpectrumLight");
    model.result("pg4").feature("vol1").set("colortabletrans", "none");
    model.result("pg4").feature("vol1").set("colorscalemode", "linear");
    model.result("pg4").feature("vol1").set("resolution", "custom");
    model.result("pg4").feature("vol1").set("refine", 2);
    model.result("pg4").feature("vol1").create("def", "Deform");
    model.result("pg4").feature("vol1").feature("def").set("expr", new String[]{"u_solid", "v_solid", "w_solid"});
    model.result("pg4").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg4").label("\u4f4d\u79fb (solid)");
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 21, 0);
    model.result("pg4").selection().geom("geom1", 3);
    model.result("pg4").selection().named("sel2");
    model.result("pg4").run();
    model.result("pg4").feature("vol1").set("unit", "\u00b5m");
    model.result("pg4").run();
    model.result("pg4").feature("vol1").feature("def").set("scaleactive", true);
    model.result("pg4").feature("vol1").feature("def").set("scale", 300);
    model.result("pg4").run();

    model.title("\u8840\u7ba1\u7f51\u7edc\u4e2d\u7684\u6d41-\u56fa\u8026\u5408");

    model
         .description("\u672c\u4f8b\u7814\u7a76\u5e7c\u513f\u7684\u8840\u7ba1\u7cfb\u7edf\u3002\u4e3b\u52a8\u8109\u53ca\u5176\u7f51\u72b6\u8840\u7ba1\u5d4c\u5165\u5728\u751f\u7269\u7ec4\u7ec7\u4e2d\u3002\u6a21\u578b\u5206\u6790\u8840\u6db2\u6d41\u52a8\u4ea7\u751f\u7684\u538b\u529b\u4f7f\u8840\u7ba1\u58c1\u53d1\u751f\u53d8\u5f62\u7684\u60c5\u51b5\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("blood_vessel.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
