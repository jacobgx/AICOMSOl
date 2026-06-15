/*
 * laminar_mixer_particle.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:54 by COMSOL 6.3.0.290. */
public class laminar_mixer_particle {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Particle_Tracing_Module\\Fluid_Flow");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);

    model.component("comp1").geom("geom1").insertFile("laminar_mixer_particle_geom_sequence.mph", "geom1");

    model.param().set("u_av", "1[cm/s]");
    model.param().descr("u_av", "\u5e73\u5747\u901f\u5ea6");

    model.component("comp1").selection().create("sel1", "Explicit");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").selection("sel1").label("\u5916\u8868\u9762");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").set("groupcontang", true);
    model.component("comp1").selection("sel1").add(1, 8, 19, 20, 21, 22, 23, 48, 49, 50, 57, 58);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u53f6\u7247\u8868\u9762");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2")
         .set(2, 3, 4, 5, 6, 7, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 51, 52, 53, 54, 55, 56);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"1000"});
    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", new String[]{"1e-3"});

    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 2);
    model.component("comp1").physics("spf").feature("inl1").selection().set(23);
    model.component("comp1").physics("spf").feature("inl1").set("BoundaryCondition", "FullyDevelopedFlow");
    model.component("comp1").physics("spf").feature("inl1").set("Uavfdf", "u_av");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 2);
    model.component("comp1").physics("spf").feature("out1").selection().set(20);

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(5, 16, 17, 18, 53, 54, 55);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("table", "cfd");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hauto", 1);
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 1);
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hcurve", 0.15);
    model.component("comp1").mesh("mesh1").create("ftri2", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri2").selection().set(23);
    model.component("comp1").mesh("mesh1").feature("ftri2").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri2").feature("size1").set("table", "cfd");
    model.component("comp1").mesh("mesh1").feature("ftri2").feature("size1").set("hauto", 2);
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").run();

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
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 21, 22, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58);
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u538b\u529b (spf)");
    model.result("pg2").set("data", "surf1");
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
    model.result("pg1").run();

    model.component("comp1").physics().create("fpt", "FluidParticleTracing", "geom1");

    model.study("std1").feature("stat").setSolveFor("/physics/fpt", false);
    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/spf", false);
    model.study("std2").feature("time").setSolveFor("/physics/fpt", true);

    model.component("comp1").physics("fpt").prop("Formulation")
         .setIndex("Formulation", "NewtonianIgnoreInertialTerms", 0);
    model.component("comp1").physics("fpt").create("df1", "DragForce", 3);
    model.component("comp1").physics("fpt").feature("df1").selection().all();
    model.component("comp1").physics("fpt").feature("df1").set("u_src", "root.comp1.u");
    model.component("comp1").physics("fpt").feature("df1").set("mu_mat", "root.comp1.spf.mu");
    model.component("comp1").physics("fpt").feature("df1").set("IncludeVirtualMassAndPressureGradientForces", true);
    model.component("comp1").physics("fpt").create("inl1", "Inlet", 2);
    model.component("comp1").physics("fpt").feature("inl1").selection().set(23);
    model.component("comp1").physics("fpt").feature("inl1").set("InitialPosition", "Density");
    model.component("comp1").physics("fpt").feature("inl1").setIndex("N", 3000, 0);
    model.component("comp1").physics("fpt").feature("inl1").set("dpro", "spf.U");
    model.component("comp1").physics("fpt").create("pcnt1", "ParticleCounter", 2);
    model.component("comp1").physics("fpt").feature("pcnt1").selection().set(20);
    model.component("comp1").physics("fpt").feature("pcnt1").set("ReleaseFeature", "inl1");
    model.component("comp1").physics("fpt").feature("pp1").set("rhop_mat", "userdef");
    model.component("comp1").physics("fpt").feature("pp1").set("dp", "5E-7[m]");

    model.study("std2").feature("time").set("tlist", "range(0,0.2,5)");
    model.study("std2").feature("time").set("usertol", true);
    model.study("std2").feature("time").set("rtol", "1e-3");
    model.study("std2").feature("time").set("usesol", true);
    model.study("std2").feature("time").set("notsolmethod", "sol");
    model.study("std2").feature("time").set("notstudy", "std1");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().dataset().create("part1", "Particle");
    model.result().dataset("part1").set("solution", "sol2");
    model.result().dataset("part1").set("posdof", new String[]{"comp1.qx", "comp1.qy", "comp1.qz"});
    model.result().dataset("part1").set("geom", "geom1");
    model.result().dataset("part1").set("pgeom", "pgeom_fpt");
    model.result().dataset("part1").set("pgeomspec", "fromphysics");
    model.result().dataset("part1").set("physicsinterface", "fpt");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "part1");
    model.result("pg3").setIndex("looplevel", 26, 0);
    model.result("pg3").label("\u7c92\u5b50\u8f68\u8ff9 (fpt)");
    model.result("pg3").create("traj1", "ParticleTrajectories");
    model.result("pg3").feature("traj1").set("pointtype", "point");
    model.result("pg3").feature("traj1").set("linetype", "none");
    model.result("pg3").feature("traj1").create("col1", "Color");
    model.result("pg3").feature("traj1").feature("col1").set("expr", "fpt.V");
    model.result("pg3").run();
    model.result("pg3").set("showlegends", false);
    model.result("pg3").set("titletype", "none");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", "1");
    model.result("pg3").feature("surf1").set("coloring", "uniform");
    model.result("pg3").feature("surf1").set("color", "black");
    model.result("pg3").feature("surf1").create("sel1", "Selection");
    model.result("pg3").feature("surf1").feature("sel1").selection().named("sel2");
    model.result("pg3").run();
    model.result("pg3").create("surf2", "Surface");
    model.result("pg3").feature("surf2").set("expr", "1");
    model.result("pg3").feature("surf2").set("coloring", "uniform");
    model.result("pg3").feature("surf2").set("color", "gray");
    model.result("pg3").feature("surf2").create("sel1", "Selection");
    model.result("pg3").feature("surf2").feature("sel1").selection().named("sel1");
    model.result("pg3").run();
    model.result("pg3").feature("surf2").create("tran1", "Transparency");
    model.result("pg3").run();
    model.result("pg3").feature("surf2").feature("tran1").set("uniformblending", 1);
    model.result("pg3").run();
    model.result("pg3").feature("traj1").set("pointtype", "comettail");
    model.result("pg3").run();
    model.result("pg3").feature("traj1").feature("col1").set("expr", "at(0,qx<0)");
    model.result("pg3").feature("traj1").feature("col1").set("colortable", "RainbowLight");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 1, 0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 6, 0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 11, 0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 16, 0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 21, 0);
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 26, 0);
    model.result("pg3").run();
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").feature("traj1").set("pointtype", "none");
    model.result("pg4").feature("traj1").set("linetype", "line");
    model.result("pg4").run();
    model.result("pg4").feature("traj1").feature("col1").set("expr", "at(0,qx)");
    model.result("pg4").feature("traj1").feature("col1").set("colortable", "Dipole");
    model.result("pg4").run();
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").set("data", "dset2");
    model.result().numerical("gev1").setIndex("looplevelinput", "last", 0);
    model.result().numerical("gev1").set("expr", new String[]{"fpt.pcnt1.alpha"});
    model.result().numerical("gev1").set("descr", new String[]{"\u4f20\u8f93\u6982\u7387"});
    model.result().numerical("gev1").set("unit", new String[]{"1"});
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u5168\u5c40\u8ba1\u7b97 1");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();
    model.result().dataset().create("cpl1", "CutPlane");
    model.result().dataset("cpl1").set("data", "part1");
    model.result().dataset("cpl1").set("quickplane", "xz");
    model.result().dataset("cpl1").set("quicky", 0.006);
    model.result().dataset("cpl1").set("genparaactive", true);
    model.result().dataset("cpl1").set("genparadist", "0.006 0.016 0.026 0.036 0.042");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").run();
    model.result("pg5").label("\u5e9e\u52a0\u83b1\u56fe");
    model.result("pg5").set("data", "part1");
    model.result("pg5").set("titletype", "none");
    model.result("pg5").create("poma1", "PoincareMap");
    model.result("pg5").feature("poma1").set("data", "cpl1");
    model.result("pg5").feature("poma1").set("sphereradiusscaleactive", true);
    model.result("pg5").feature("poma1").set("sphereradiusscale", 0.4);
    model.result("pg5").run();
    model.result("pg5").feature("poma1").create("col1", "Color");
    model.result("pg5").run();
    model.result("pg5").feature("poma1").feature("col1").set("expr", "at(0,qx<0)");
    model.result("pg5").feature("poma1").feature("col1").set("colortable", "RainbowLight");
    model.result("pg5").feature("poma1").feature("col1").set("colorlegend", false);
    model.result("pg5").run();
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("data", "cpl1");
    model.result("pg5").feature("surf1").set("expr", "1");
    model.result("pg5").feature("surf1").set("coloring", "uniform");
    model.result("pg5").feature("surf1").set("color", "gray");
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").run();
    model.result("pg6").label("\u76f8\u56fe");
    model.result("pg6").set("edges", false);
    model.result("pg6").set("data", "part1");
    model.result("pg6").create("phpo1", "PhasePortrait");
    model.result("pg6").feature("phpo1").set("xdata", "manual");
    model.result("pg6").feature("phpo1").set("xmanual", "comp1.qx");
    model.result("pg6").feature("phpo1").set("ydata", "manual");
    model.result("pg6").feature("phpo1").set("ymanual", "comp1.qz");
    model.result("pg6").feature("phpo1").create("col1", "Color");
    model.result("pg6").run();
    model.result("pg6").feature("phpo1").feature("col1").set("colorlegend", false);
    model.result("pg6").feature("phpo1").feature("col1").set("expr", "at(0,qx<0)");
    model.result("pg6").feature("phpo1").feature("col1").set("colortable", "RainbowLight");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").set("view", "view2");
    model.result("pg6").run();
    model.result("pg6").setIndex("looplevel", 1, 0);
    model.result("pg6").run();
    model.result("pg6").setIndex("looplevel", 6, 0);
    model.result("pg6").run();
    model.result("pg6").setIndex("looplevel", 11, 0);
    model.result("pg6").run();
    model.result("pg6").setIndex("looplevel", 16, 0);
    model.result("pg6").run();
    model.result("pg6").setIndex("looplevel", 21, 0);
    model.result("pg6").run();
    model.result("pg6").setIndex("looplevel", 26, 0);
    model.result("pg6").run();

    model.title("\u5c42\u6d41\u9759\u6001\u6df7\u5408\u5668\u4e2d\u7684\u9897\u7c92\u8f68\u8ff9");

    model
         .description("\u672c\u4f8b\u8ba1\u7b97\u901a\u8fc7\u9759\u6001\u6df7\u5408\u88c5\u7f6e\u7684\u77f3\u82f1\u9897\u7c92\u7684\u8f68\u8ff9\u3002\u9897\u7c92\u7684\u60ef\u6027\u5bfc\u81f4\u5176\u4e2d\u4e00\u4e9b\u9897\u7c92\u4e0e\u901a\u9053\u58c1\u6216\u6df7\u5408\u53f6\u7247\u78b0\u649e\uff0c\u56e0\u6b64\u53ea\u6709\u4e00\u5b9a\u6bd4\u4f8b\u7684\u9897\u7c92\u5230\u8fbe\u51fa\u53e3\u3002\u672c\u4f8b\u5728\u540e\u5904\u7406\u8fc7\u7a0b\u4e2d\u8ba1\u7b97\u8fd9\u4e00\u6bd4\u4f8b\uff0c\u5373\u4f20\u8f93\u6982\u7387\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("laminar_mixer_particle.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
