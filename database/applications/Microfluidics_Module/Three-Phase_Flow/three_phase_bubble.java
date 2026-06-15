/*
 * three_phase_bubble.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:10 by COMSOL 6.3.0.290. */
public class three_phase_bubble {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Microfluidics_Module\\Three-Phase_Flow");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");
    model.component("comp1").physics().create("terpf", "TernaryPhaseField", "geom1");

    model.component("comp1").multiphysics().create("tfpf1", "TernaryFlowPhaseField", 2);
    model.component("comp1").multiphysics("tfpf1").set("Fluid_physics", "spf");
    model.component("comp1").multiphysics("tfpf1").set("MovingInterface_physics", "terpf");
    model.component("comp1").multiphysics("tfpf1").selection().all();

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/spf", true);
    model.study("std1").feature("time").setSolveFor("/physics/terpf", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/tfpf1", true);

    model.component("comp1").geom("geom1").lengthUnit("cm");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param()
         .set("sigma_AB", "0.05[N/m]", "\u4e24\u79cd\u6d41\u4f53\u754c\u9762\u95f4\u7684\u8868\u9762\u5f20\u529b\u7cfb\u6570");
    model.param()
         .set("sigma_AC", "0.07[N/m]", "\u8f7b\u6d41\u4f53\u4e0e\u6c14\u4f53\u754c\u9762\u95f4\u7684\u8868\u9762\u5f20\u529b\u7cfb\u6570");
    model.param()
         .set("sigma_BC", "0.07[N/m]", "\u91cd\u6d41\u4f53\u4e0e\u6c14\u4f53\u754c\u9762\u95f4\u7684\u8868\u9762\u5f20\u529b\u7cfb\u6570");
    model.param().set("mu_A", "0.1[Pa*s]", "\u6db2\u4f53\u7684\u52a8\u529b\u9ecf\u5ea6");
    model.param().set("mu_B", "0.15[Pa*s]", "\u6c14\u4f53\u7684\u52a8\u529b\u9ecf\u5ea6");
    model.param().set("mu_C", "1e-4[Pa*s]", "\u6c14\u4f53\u7684\u52a8\u529b\u9ecf\u5ea6");
    model.param().set("width", "3.2[cm]", "\u5bb9\u5668\u5bbd\u5ea6");
    model.param().set("height", "14[cm]", "\u5bb9\u5668\u9ad8\u5ea6");
    model.param().set("radius", "0.8[cm]", "\u6c14\u6ce1\u534a\u5f84");
    model.param().set("center_rec", "5.5[cm]", "\u5bb9\u5668\u4e2d\u5fc3");
    model.param().set("line_z", "2[cm]", "\u6db2\u5c42\u754c\u9762\u4f4d\u7f6e");
    model.param().set("center_bub", "0.15[cm]", "\u6c14\u6ce1\u4e2d\u5fc3");
    model.param().set("rho_A", "1000[kg/m^3]", "\u8f7b\u6d41\u4f53\u7684\u5bc6\u5ea6");
    model.param().set("rho_B", "1200[kg/m^3]", "\u91cd\u6d41\u4f53\u7684\u5bc6\u5ea6");
    model.param().set("rho_C", "1[kg/m^3]", "\u6c14\u4f53\u5bc6\u5ea6");
    model.param().set("tend", "0.65[s]", "\u4eff\u771f\u65f6\u95f4\u5468\u671f");
    model.param().set("delta_t", "0.025[s]", "\u4fdd\u5b58\u7684\u65f6\u95f4\u95f4\u9694");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("M0", "M0const*nojac(M0factor)", "\u8fc1\u79fb\u7387\u8c03\u6574\u53c2\u6570");
    model.component("comp1").variable("var1")
         .set("M0factor", "phiA^2*phiB^2+terpf.phiC^2*phiA^2+terpf.phiC^2*phiB^2", "\u52a9\u53d8\u91cf");
    model.component("comp1").variable("var1").set("M0const", "2e-5[m^3/s]", "\u52a9\u53d8\u91cf");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"width/2", "height"});
    model.component("comp1").geom("geom1").feature("r1").set("base", "center");
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"width/4", "center_rec"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", "radius");
    model.component("comp1").geom("geom1").feature("c1").set("angle", 180);
    model.component("comp1").geom("geom1").feature("c1").set("pos", new String[]{"0", "center_bub"});
    model.component("comp1").geom("geom1").feature("c1").set("rot", -90);
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 0, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "line_z", 0, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "radius*2", 1, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "line_z", 1, 1);
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").physics("terpf").feature("mp1").set("M0", "M0");
    model.component("comp1").physics("terpf").feature("mp1").set("SurfaceTensionCoefficientAB", "userdef");
    model.component("comp1").physics("terpf").feature("mp1").set("sigma_AB", "sigma_AB");
    model.component("comp1").physics("terpf").feature("mp1").set("SurfaceTensionCoefficientBC", "userdef");
    model.component("comp1").physics("terpf").feature("mp1").set("sigma_BC", "sigma_BC");
    model.component("comp1").physics("terpf").feature("mp1").set("SurfaceTensionCoefficientAC", "userdef");
    model.component("comp1").physics("terpf").feature("mp1").set("sigma_AC", "sigma_AC");
    model.component("comp1").physics("terpf").create("init2", "init", 2);
    model.component("comp1").physics("terpf").feature("init2").selection().set(1);
    model.component("comp1").physics("terpf").feature("init2").set("phiB", 1);
    model.component("comp1").physics("terpf").create("init3", "init", 2);
    model.component("comp1").physics("terpf").feature("init3").selection().set(2);
    model.component("comp1").physics("terpf").feature("init1").set("phiA", 1);

    model.component("comp1").material().create("mpmat1", "Multiphase");
    model.component("comp1").material("mpmat1").selection().geom("geom1", 2);
    model.component("comp1").material("mpmat1").selection().set(1, 2, 3);
    model.component("comp1").material("mpmat1").selection().inherit(false);
    model.component("comp1").material("mpmat1").selection().embedded(false);
    model.component("comp1").material("mpmat1").set("vfDefinition", "terpf");
    model.component("comp1").material("mpmat1").feature().create("phase2", "PhaseLink", "comp1");
    model.component("comp1").material("mpmat1").feature().create("phase3", "PhaseLink", "comp1");
    model.component("comp1").material("mpmat1").set("constrainedPhase", "phase3");

    model.component("comp1").multiphysics("tfpf1").set("multiphaseMaterialList", "mpmat1");

    model.component("comp1").material("mpmat1").feature("phase1").propertyGroup("def")
         .set("density", new String[]{"rho_A"});
    model.component("comp1").material("mpmat1").feature("phase1").propertyGroup("def")
         .set("dynamicviscosity", new String[]{"mu_A"});
    model.component("comp1").material("mpmat1").feature("phase1").label("\u6d41\u4f53 A");
    model.component("comp1").material("mpmat1").feature("phase2").label("\u6d41\u4f53 B");
    model.component("comp1").material("mpmat1").feature("phase2").propertyGroup("def")
         .set("density", new String[]{"rho_B"});
    model.component("comp1").material("mpmat1").feature("phase2").propertyGroup("def")
         .set("dynamicviscosity", new String[]{"mu_B"});
    model.component("comp1").material("mpmat1").feature("phase3").propertyGroup("def")
         .set("density", new String[]{"rho_C"});
    model.component("comp1").material("mpmat1").feature("phase3").propertyGroup("def")
         .set("dynamicviscosity", new String[]{"mu_C"});
    model.component("comp1").material("mpmat1").feature("phase3").label("\u6d41\u4f53 C");

    model.component("comp1").func().create("rm1", "Ramp");
    model.component("comp1").func("rm1").set("slope", 200);
    model.component("comp1").func("rm1").set("cutoffactive", true);

    model.component("comp1").physics("spf").prop("PhysicalModelProperty").set("IncludeGravity", true);
    model.component("comp1").physics("spf").feature("grav1")
         .set("g", new String[]{"0", "0", "-g_const*rm1(t[1/s])"});
    model.component("comp1").physics("spf").create("prpc1", "PressurePointConstraint", 0);
    model.component("comp1").physics("spf").feature("prpc1").selection().set(8);

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", 0.04);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tlist", "range(0,0.025,1)*0.65");
    model.study("std1").showAutoSequences("all");
    model.study("std1").feature("time").set("plot", true);
    model.study("std1").feature("time").set("plotfreq", "tsteps");

    model.sol("sol1").feature("t1").set("maxstepconstraintbdf", "const");
    model.sol("sol1").feature("t1").set("maxstepbdf", "0.0005");

    model.study("std1").createAutoSequences("sol");
    model.study("std1").createAutoSequences("jobs");

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

    model.sol("sol1").runFromTo("st1", "v1");

    model.result().remove("pg1");

    model.study("std1").feature("time").set("plotgroup", "Default");

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
    model.result("pg4").label("\u4e8c\u7ef4\u7ed8\u56fe\u7ec4\uff1a\u76f8 A (terpf)");
    model.result("pg4").set("dataisaxisym", "off");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").label("\u76f8\u573a\u53d8\u91cf\uff0c\u76f8 A");
    model.result("pg4").feature("surf1").set("expr", "phiA");
    model.result("pg4").feature("surf1").set("smooth", "internal");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").label("\u4e8c\u7ef4\u7ed8\u56fe\u7ec4\uff1a\u76f8 B (terpf)");
    model.result("pg5").set("dataisaxisym", "off");
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").feature().create("surf1", "Surface");
    model.result("pg5").feature("surf1").label("\u76f8\u573a\u53d8\u91cf\uff0c\u76f8 B");
    model.result("pg5").feature("surf1").set("expr", "phiB");
    model.result("pg5").feature("surf1").set("smooth", "internal");
    model.result("pg5").feature("surf1").set("data", "parent");
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").label("\u4e8c\u7ef4\u7ed8\u56fe\u7ec4\uff1a\u76f8 C (terpf)");
    model.result("pg6").set("dataisaxisym", "off");
    model.result("pg6").set("frametype", "spatial");
    model.result("pg6").feature().create("surf1", "Surface");
    model.result("pg6").feature("surf1").label("\u76f8\u573a\u53d8\u91cf\uff0c\u76f8 C");
    model.result("pg6").feature("surf1").set("expr", "terpf.phiC");
    model.result("pg6").feature("surf1").set("smooth", "internal");
    model.result("pg6").feature("surf1").set("data", "parent");
    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").label("\u4e8c\u7ef4\u7ed8\u56fe\u7ec4\uff1a\u4e09\u76f8 (terpf)");
    model.result("pg7").set("dataisaxisym", "off");
    model.result("pg7").set("edges", "off");
    model.result("pg7").feature().create("surf1", "Surface");
    model.result("pg7").feature("surf1").label("\u4e09\u5143\u76f8\u573a");
    model.result("pg7").feature("surf1").set("expr", "phiA+2*phiB+3*terpf.phiC");
    model.result("pg7").feature("surf1").set("smooth", "internal");
    model.result("pg7").feature("surf1").set("data", "parent");
    model.result("pg1").run();

    model.study("std1").feature("time").set("plotgroup", "pg7");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").run();
    model.result("pg8").create("iso1", "Isosurface");
    model.result("pg8").feature("iso1").set("expr", "phiB");
    model.result("pg8").feature("iso1").set("levelmethod", "levels");
    model.result("pg8").feature("iso1").set("levels", 0.5);
    model.result("pg8").feature("iso1").set("titletype", "none");
    model.result("pg8").feature("iso1").set("coloring", "uniform");
    model.result("pg8").feature("iso1").set("colorlegend", false);
    model.result("pg8").feature("iso1").set("color", "magenta");
    model.result("pg8").run();
    model.result("pg8").create("iso2", "Isosurface");
    model.result("pg8").feature("iso2").set("expr", "terpf.phiC");
    model.result("pg8").feature("iso2").set("levelmethod", "levels");
    model.result("pg8").feature("iso2").set("levels", 0.5);
    model.result("pg8").feature("iso2").set("coloring", "uniform");
    model.result("pg8").feature("iso2").set("titletype", "none");
    model.result("pg8").feature("iso2").set("colorlegend", false);
    model.result("pg8").feature("iso2").set("color", "gray");
    model.result("pg8").run();
    model.result("pg8").run();
    model.result("pg8").create("slc1", "Slice");
    model.result("pg8").feature("slc1").set("quickxnumber", 1);
    model.result("pg8").feature("slc1").set("quickplane", "zx");
    model.result("pg8").feature("slc1").set("quickynumber", 1);
    model.result("pg8").feature("slc1").create("def1", "Deform");
    model.result("pg8").feature("slc1").feature("def1").set("revcoordsys", "cylindrical");
    model.result("pg8").run();
    model.result("pg8").feature("slc1").feature("def1").set("revcoordsys", "cartesian");
    model.result("pg8").feature("slc1").feature("def1").set("expr", new String[]{"0", "sqrt(0.016^2-r^2)", ""});
    model.result("pg8").feature("slc1").feature("def1").setIndex("expr", 0, 2);
    model.result("pg8").feature("slc1").feature("def1").set("scaleactive", true);
    model.result("pg8").feature("slc1").feature("def1").set("scale", 1);
    model.result("pg8").run();
    model.result("pg8").run();
    model.result("pg8").setIndex("looplevel", 1, 0);
    model.result("pg8").set("showlegends", false);
    model.result("pg8").set("titletype", "none");
    model.result("pg8").run();
    model.result("pg8").setIndex("looplevel", 14, 0);
    model.result("pg8").run();
    model.result("pg8").setIndex("looplevel", 26, 0);
    model.result("pg8").run();
    model.result("pg8").setIndex("looplevel", 38, 0);
    model.result("pg8").run();
    model.result().numerical().create("int1", "IntSurface");
    model.result().numerical("int1").set("intvolume", true);
    model.result().numerical("int1").selection().all();
    model.result().numerical("int1").setIndex("expr", "tfpf1.VfA/8.447E-5[m^3]", 0);
    model.result().numerical("int1").setIndex("expr", "tfpf1.VfB/2.603e-5[m^3]", 1);
    model.result().numerical("int1").setIndex("expr", "tfpf1.VfC/2.1451e-6[m^3]", 2);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u8868\u9762\u79ef\u5206 1");
    model.result().numerical("int1").set("table", "tbl1");
    model.result().numerical("int1").setResult();
    model.result().create("pg9", "PlotGroup1D");
    model.result("pg9").set("data", "none");
    model.result("pg9").create("tblp1", "Table");
    model.result("pg9").feature("tblp1").set("source", "table");
    model.result("pg9").feature("tblp1").set("table", "tbl1");
    model.result("pg9").feature("tblp1").set("linewidth", "preference");
    model.result("pg9").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg9").run();
    model.result("pg9").feature("tblp1").set("legend", true);
    model.result("pg9").feature("tblp1").set("legendmethod", "manual");
    model.result("pg9").feature("tblp1").setIndex("legends", "\u8f7b\u6d41\u4f53\u76f8\u5bf9\u8d28\u91cf", 0);
    model.result("pg9").feature("tblp1").setIndex("legends", "\u91cd\u6d41\u4f53\u76f8\u5bf9\u8d28\u91cf", 1);
    model.result("pg9").feature("tblp1").setIndex("legends", "\u6c14\u4f53\u76f8\u5bf9\u8d28\u91cf", 2);

    model.title("\u5206\u5c42\u6db2\u5c42\u4e4b\u95f4\u7684\u6c14\u6ce1\u8bf1\u5bfc\u5939\u5e26");

    model
         .description("\u672c\u6a21\u578b\u662f\u98df\u54c1\u52a0\u5de5\u3001\u5236\u836f\u5de5\u4e1a\u548c\u5316\u5de5\u9886\u57df\u5e38\u7528\u7684\u4e09\u76f8\u6d41\u57fa\u51c6\u6a21\u578b\uff0c\u4eff\u771f\u7ed3\u679c\u6839\u636e\u6587\u732e\u62a5\u9053\u7684\u6570\u636e\u8fdb\u884c\u4e86\u9a8c\u8bc1\u3002\n\n\u6c14\u6ce1\u4e0a\u5347\uff0c\u7a7f\u8fc7\u4e24\u4e2a\u6db2\u5c42\uff0c\u5176\u4e2d\u8f83\u8f7b\u7684\u6db2\u4f53\u4f4d\u4e8e\u8f83\u91cd\u6db2\u4f53\u7684\u4e0a\u65b9\u3002\u5f53\u6c14\u6ce1\u79bb\u5f00\u8f83\u91cd\u7684\u6db2\u4f53\u65f6\uff0c\u4f1a\u5728\u5c3e\u6d41\u4e2d\u5939\u5e26\u4e00\u4e9b\u8f83\u91cd\u6db2\u4f53\uff0c\u5c06\u5176\u5e26\u5165\u8f83\u8f7b\u7684\u6db2\u4f53\u4e2d\uff0c\u4ece\u800c\u4ea7\u751f\u4e00\u6761\u8f83\u91cd\u6db2\u4f53\u7684\u201c\u5c3e\u5df4\u201d\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("three_phase_bubble.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
