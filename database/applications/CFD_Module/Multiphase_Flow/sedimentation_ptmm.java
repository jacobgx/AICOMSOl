/*
 * sedimentation_ptmm.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:32 by COMSOL 6.3.0.290. */
public class sedimentation_ptmm {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\CFD_Module\\Multiphase_Flow");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("spf", "TurbulentFlowkeps", "geom1");
    model.component("comp1").physics("spf").prop("PhysicalModelProperty")
         .set("Compressibility", "CompressibleMALT03");
    model.component("comp1").physics().create("phtr", "PhaseTransportFree", "geom1");

    model.component("comp1").multiphysics().create("mfmm1", "MultiphaseFlowMixtureModel", 2);
    model.component("comp1").multiphysics("mfmm1").set("multiphaseflow_physics", "phtr");
    model.component("comp1").multiphysics("mfmm1").set("fluidflow_physics", "spf");
    model.component("comp1").multiphysics("mfmm1").selection().all();

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/spf", true);
    model.study("std1").feature("time").setSolveFor("/physics/phtr", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/mfmm1", true);

    model.component("comp1").geom("geom1").insertFile("sedimentation_ptmm_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("mce1", "MeshControlEdges");
    model.component("comp1").geom("geom1").feature("mce1").selection("input").set("fin", 8, 10, 11);
    model.component("comp1").geom("geom1").run("mce1");

    model.param().set("rho_c", "1000[kg/m^3]");
    model.param().descr("rho_c", "\u8fde\u7eed\u76f8\u5bc6\u5ea6");
    model.param().set("mu_c", "1e-3[Pa*s]");
    model.param().descr("mu_c", "\u8fde\u7eed\u76f8\u9ecf\u5ea6");
    model.param().set("rho_d", "1100[kg/m^3]");
    model.param().descr("rho_d", "\u5206\u6563\u76f8\u5bc6\u5ea6");
    model.param().set("d_d", "0.2[mm]");
    model.param().descr("d_d", "\u5206\u6563\u76f8\u7c92\u5f84");

    model.component("comp1").func().create("step1", "Step");
    model.component("comp1").func("step1").set("location", 1);
    model.component("comp1").func("step1").set("smooth", 2);

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("v_in", "1.25*step1(t[1/s])[m/s]");
    model.component("comp1").variable("var1").descr("v_in", "\u5165\u53e3\u901f\u5ea6");
    model.component("comp1").variable("var1").set("v_out", "0.05*step1(t[1/s])[m/s]");
    model.component("comp1").variable("var1").descr("v_out", "\u51fa\u53e3\u901f\u5ea6");
    model.component("comp1").variable("var1").set("phid_in", "0.003*step1(t[1/s])");
    model.component("comp1").variable("var1")
         .descr("phid_in", "\u5165\u53e3\u5904\u5206\u6563\u76f8\u4f53\u79ef\u5206\u6570");
    model.component("comp1").variable("var1")
         .set("qd_out", "2*pi*r*s2*(mfmm1.u_s2r*nr+mfmm1.u_s2z*nz)*mfmm1.rho_s2");
    model.component("comp1").variable("var1")
         .descr("qd_out", "\u5206\u6563\u76f8\u8d28\u91cf\u51fa\u53e3\u6d41\u91cf");

    model.component("comp1").physics("spf").prop("PhysicalModelProperty").set("IncludeGravity", true);
    model.component("comp1").physics("spf").feature("init1").set("p_init", "-g_const*z*rho_c");
    model.component("comp1").physics("spf").feature("init1")
         .set("CompensateForHydrostaticPressureApproximation", false);
    model.component("comp1").physics("spf").create("wallbc2", "WallBC", 1);
    model.component("comp1").physics("spf").feature("wallbc2").set("BoundaryCondition", "Slip");
    model.component("comp1").physics("spf").feature("wallbc2").selection().set(7);
    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 1);
    model.component("comp1").physics("spf").feature("inl1").selection().set(5);
    model.component("comp1").physics("spf").feature("inl1").set("U0in", "v_in");
    model.component("comp1").physics("spf").feature("inl1").set("IT_list", "user_defined");
    model.component("comp1").physics("spf").feature("inl1").set("LT_list", "user_defined");
    model.component("comp1").physics("spf").feature("inl1").set("LT", "0.2*0.07");
    model.component("comp1").physics("spf").feature("inl1").set("constraintType", "symmetricConstraint");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 1);
    model.component("comp1").physics("spf").feature("out1").selection().set(2);
    model.component("comp1").physics("spf").feature("out1").set("BoundaryCondition", "Velocity");
    model.component("comp1").physics("spf").feature("out1").set("U0out", "v_out");
    model.component("comp1").physics("spf").feature("out1").set("constraintType", "symmetricConstraint");
    model.component("comp1").physics("spf").create("out2", "OutletBoundary", 1);
    model.component("comp1").physics("spf").feature("out2").selection().set(18);
    model.component("comp1").physics("spf").feature("out2").set("constraintType", "symmetricConstraint");
    model.component("comp1").physics("phtr").create("sa1", "VolumeFraction", 1);
    model.component("comp1").physics("phtr").feature("sa1").selection().set(5);
    model.component("comp1").physics("phtr").feature("sa1").setIndex("phases", true, 1);
    model.component("comp1").physics("phtr").feature("sa1").setIndex("s0", "phid_in", 1);
    model.component("comp1").physics("phtr").create("of1", "Outflow", 1);
    model.component("comp1").physics("phtr").feature("of1").selection().set(2, 18);

    model.component("comp1").multiphysics("mfmm1").set("SlipModel", "HadamardRybczynski");
    model.component("comp1").multiphysics("mfmm1").set("rhoc_mat", "userdef");
    model.component("comp1").multiphysics("mfmm1").set("rhoc", "rho_c");
    model.component("comp1").multiphysics("mfmm1").set("muc_mat", "userdef");
    model.component("comp1").multiphysics("mfmm1").set("muc", "mu_c");
    model.component("comp1").multiphysics("mfmm1").set("rho_s2_mat", "userdef");
    model.component("comp1").multiphysics("mfmm1").set("rho_s2", "rho_d");
    model.component("comp1").multiphysics("mfmm1").set("diam_s2", "d_d");

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 2);
    model.component("comp1").mesh("mesh1").feature("size1").set("hauto", 1);
    model.component("comp1").mesh("mesh1").feature("cr1").active(false);
    model.component("comp1").mesh("mesh1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature().move("size2", 2);
    model.component("comp1").mesh("mesh1").feature("size2").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("size2").selection().set(26, 27, 28);
    model.component("comp1").mesh("mesh1").feature("size2").set("table", "cfd");
    model.component("comp1").mesh("mesh1").feature("size2").set("hauto", 1);
    model.component("comp1").mesh("mesh1").feature("ftri1").set("smoothmaxiter", 8);
    model.component("comp1").mesh("mesh1").feature("ftri1").set("smoothmaxdepth", 8);
    model.component("comp1").mesh("mesh1").feature("bl1").set("sharpcorners", "split");
    model.component("comp1").mesh("mesh1").feature("bl1").set("smoothmaxiter", 8);
    model.component("comp1").mesh("mesh1").feature("bl1").set("smoothmaxdepth", 16);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp1").set("blnlayers", 12);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp1").set("blhminfact", 1);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time")
         .set("tlist", "range(0,0.01,0.1) range(1,10) 100*range(1,10) 1800*range(1,24)");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("v1").feature("comp1_ep").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_ep").set("scaleval", 0.1);
    model.sol("sol1").feature("v1").feature("comp1_k").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_k").set("scaleval", 0.1);
    model.sol("sol1").feature("v1").feature("comp1_p").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_p").set("scaleval", "1e4");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("t1").set("initialstepbdfactive", true);
    model.sol("sol1").feature("t1").set("initialstepbdf", 0.005);
    model.sol("sol1").feature("t1").feature("se1").set("ratelimitactive", true);
    model.sol("sol1").feature("t1").feature("se1").set("ratelimit", 10);

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
    model.result().dataset().create("edg1", "Edge2D");
    model.result().dataset("edg1").label("\u5916\u58c1");
    model.result().dataset("edg1").set("data", "dset1");
    model.result().dataset("edg1").selection().geom("geom1", 1);
    model.result().dataset("edg1").selection()
         .set(3, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 19, 20, 21, 22, 23, 24, 25, 26);
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("\u58c1\u5206\u8fa8\u7387 (spf)");
    model.result("pg4").set("dataisaxisym", "off");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").feature().create("line1", "Line");
    model.result("pg4").feature("line1").label("\u58c1\u5206\u8fa8\u7387");
    model.result("pg4").feature("line1").set("showsolutionparams", "on");
    model.result("pg4").feature("line1").set("expr", "spf.Delta_wPlus");
    model.result("pg4").feature("line1").set("linetype", "tube");
    model.result("pg4").feature("line1").set("smooth", "internal");
    model.result("pg4").feature("line1").set("showsolutionparams", "on");
    model.result("pg4").feature("line1").set("data", "parent");
    model.result("pg4").feature("line1").feature().create("hght1", "Height");
    model.result("pg4").feature("line1").feature("hght1").label("\u9ad8\u5ea6\u8868\u8fbe\u5f0f");
    model.result("pg4").feature("line1").feature("hght1").set("heightdata", "expr");
    model.result("pg4").feature("line1").feature("hght1").set("expr", "spf.WRHeightExpr");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").label("\u4f53\u79ef\u5206\u6570 (phtr)");
    model.result("pg5").set("dataisaxisym", "off");
    model.result("pg5").feature().create("surf1", "Surface");
    model.result("pg5").feature("surf1").label("\u8868\u9762");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("expr", "s1");
    model.result("pg5").feature("surf1").set("colortable", "JupiterAuroraBorealis");
    model.result("pg5").feature("surf1").set("colortabletrans", "reverse");
    model.result("pg5").feature("surf1").set("smooth", "internal");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("data", "parent");
    model.result().dataset("rev1").set("data", "dset1");
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").label("\u4f53\u79ef\u5206\u6570 (phtr) 1");
    model.result("pg6").feature().create("surf1", "Surface");
    model.result("pg6").feature("surf1").label("\u8868\u9762");
    model.result("pg6").feature("surf1").set("showsolutionparams", "on");
    model.result("pg6").feature("surf1").set("expr", "s1");
    model.result("pg6").feature("surf1").set("colortable", "JupiterAuroraBorealis");
    model.result("pg6").feature("surf1").set("colortabletrans", "reverse");
    model.result("pg6").feature("surf1").set("smooth", "internal");
    model.result("pg6").feature("surf1").set("showsolutionparams", "on");
    model.result("pg6").feature("surf1").set("data", "parent");
    model.result("pg1").run();
    model.result().numerical().create("int1", "IntLine");
    model.result().numerical("int1").set("intsurface", true);
    model.result().numerical("int1").selection().set(5);
    model.result().numerical("int1").set("expr", new String[]{"phtr.Nz_s2"});
    model.result().numerical("int1")
         .set("descr", new String[]{"\u8d28\u91cf\u901a\u91cf\uff0c\u76f8\u201cs2\u201d\uff0cz \u5206\u91cf"});
    model.result().numerical("int1").set("unit", new String[]{"kg/s"});
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u7ebf\u79ef\u5206 1");
    model.result().numerical("int1").set("table", "tbl1");
    model.result().numerical("int1").setResult();
    model.result().numerical().create("int2", "IntLine");
    model.result().numerical("int2").set("intsurface", true);
    model.result().numerical("int2").selection().set(2);
    model.result().numerical("int2").set("expr", new String[]{"phtr.Nz_s2"});
    model.result().numerical("int2")
         .set("descr", new String[]{"\u8d28\u91cf\u901a\u91cf\uff0c\u76f8\u201cs2\u201d\uff0cz \u5206\u91cf"});
    model.result().numerical("int2").set("unit", new String[]{"kg/s"});
    model.result().numerical("int2").setIndex("expr", "-phtr.Nz_s2", 0);
    model.result().numerical("int2").set("table", "tbl1");
    model.result().numerical("int2").appendResult();
    model.result().numerical().create("int3", "IntLine");
    model.result().numerical("int3").set("intsurface", true);
    model.result().numerical("int3").selection().set(18);
    model.result().numerical("int3").set("expr", new String[]{"phtr.Nz_s2"});
    model.result().numerical("int3")
         .set("descr", new String[]{"\u8d28\u91cf\u901a\u91cf\uff0c\u76f8\u201cs2\u201d\uff0cz \u5206\u91cf"});
    model.result().numerical("int3").set("unit", new String[]{"kg/s"});
    model.result().numerical("int3").setIndex("expr", "-phtr.Nz_s2", 0);
    model.result().numerical("int3").set("table", "tbl1");
    model.result().numerical("int3").appendResult();
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").label("\u4e00\u7ef4\u7ed8\u56fe\u7ec4 7");
    model.result("pg7").set("ylabelactive", true);
    model.result("pg7").set("ylabel", "\u8d28\u91cf\u6d41 (kg/s)");
    model.result("pg7").create("tblp1", "Table");
    model.result("pg7").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg7").feature("tblp1").set("linewidth", "preference");
    model.result("pg7").feature("tblp1").set("legend", true);
    model.result("pg7").feature("tblp1").set("legendmethod", "manual");
    model.result("pg7").feature("tblp1")
         .setIndex("legends", "\u5206\u6563\u76f8\u8d28\u91cf\u5165\u53e3\u6d41\u91cf", 0);
    model.result("pg7").feature("tblp1")
         .setIndex("legends", "\u5206\u6563\u76f8\u8d28\u91cf\u51fa\u53e3\u6d41\u91cf\uff0c\u51fa\u53e3 1", 1);
    model.result("pg7").feature("tblp1")
         .setIndex("legends", "\u5206\u6563\u76f8\u8d28\u91cf\u51fa\u53e3\u6d41\u91cf\uff0c\u51fa\u53e3 2", 2);
    model.result("pg7").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature("surf1").set("expr", "s2");
    model.result("pg5").run();
    model.result("pg5").feature("surf1").set("rangecoloractive", true);
    model.result("pg5").feature("surf1").set("rangecolormin", 0);
    model.result("pg5").feature("surf1").set("rangecolormax", 0.006);
    model.result("pg5").run();
    model.result("pg5").create("str1", "Streamline");
    model.result("pg5").feature("str1").set("posmethod", "uniform");
    model.result("pg5").feature("str1").set("udist", 0.02);
    model.result("pg5").run();
    model.result().dataset("rev1").set("startangle", 0);
    model.result().dataset("rev1").set("revangle", 270);
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").feature("surf1").set("expr", "s2");
    model.result("pg6").feature("surf1").set("rangecoloractive", true);
    model.result("pg6").feature("surf1").set("rangecolormin", 0);
    model.result("pg6").feature("surf1").set("rangecolormax", 0.006);
    model.result("pg6").run();
    model.result("pg6").create("surf2", "Surface");
    model.result("pg6").feature("surf2").set("expr", "spf.Delta_wPlus");
    model.result("pg6").feature("surf2").set("descr", "\u65e0\u91cf\u7eb2\u58c1\u5206\u8fa8\u7387");
    model.result("pg6").feature("surf2").set("coloring", "uniform");
    model.result("pg6").feature("surf2").set("color", "gray");
    model.result("pg6").run();
    model.result("pg6").create("str1", "Streamline");
    model.result("pg6").feature("str1").set("revcoordsys", "cylindrical");
    model.result("pg6").feature("str1").set("expr", new String[]{"mfmm1.u_s2r", "mfmm1.u_s2phi", "mfmm1.u_s2z"});
    model.result("pg6").feature("str1").set("descr", "\u5bf9\u6d41\u901f\u5ea6\u5206\u6563\u76f8 s2");
    model.result("pg6").feature("str1").setIndex("expr", 0, 1);
    model.result("pg6").feature("str1").set("descractive", true);
    model.result("pg6").feature("str1").set("descr", "\u5206\u6563\u76f8\uff08\u9ed1\uff09");
    model.result("pg6").feature("str1").set("startmethod", "coord");
    model.result("pg6").feature("str1").set("xcoord", "range(0.01,0.02,0.19)");
    model.result("pg6").feature("str1").set("ycoord", 0);
    model.result("pg6").feature("str1").set("zcoord", "-1*1^range(1,10)");
    model.result("pg6").feature("str1").set("color", "black");
    model.result("pg6").run();
    model.result("pg6").create("str2", "Streamline");
    model.result("pg6").feature("str2").set("revcoordsys", "cylindrical");
    model.result("pg6").feature("str2").set("expr", new String[]{"mfmm1.ucontr", "mfmm1.ucontphi", "mfmm1.ucontz"});
    model.result("pg6").feature("str2").set("descr", "\u901f\u5ea6\u573a\uff0c\u8fde\u7eed\u76f8");
    model.result("pg6").feature("str2").setIndex("expr", 0, 1);
    model.result("pg6").feature("str2").set("descractive", true);
    model.result("pg6").feature("str2").set("descr", "\u8fde\u7eed\u76f8\uff08\u767d\uff09");
    model.result("pg6").feature("str2").set("startmethod", "coord");
    model.result("pg6").feature("str2").set("xcoord", "range(0,0.02,0.2) range(0.5,0.5,12)");
    model.result("pg6").feature("str2").set("ycoord", 0);
    model.result("pg6").feature("str2").set("zcoord", "-1^range(1,35)");
    model.result("pg6").feature("str2").set("color", "white");
    model.result("pg6").run();
    model.result("pg6").set("titletype", "none");
    model.result("pg6").run();

    model.view("view2").set("scenelight", false);
    model.view("view2").set("showgrid", false);
    model.view("view2").set("showaxisorientation", false);

    model
         .title("\u91c7\u7528\u6c89\u6dc0\u6cd5\u53bb\u9664\u4e8c\u6c89\u6c60\u5e9f\u6c34\u4e2d\u7684\u6c61\u67d3\u7269");

    model
         .description("\u672c\u4f8b\u7814\u7a76\u5982\u4f55\u5728\u5706\u5f62\u4e8c\u6c89\u6c60\u4e2d\u901a\u8fc7\u6c89\u6dc0\u6cd5\u53bb\u9664\u5e9f\u6c34\u4e2d\u7684\u6c61\u67d3\u7269\uff0c\u5176\u4e2d\u4f7f\u7528\u7684\u662f k-\u03b5 \u6e4d\u6d41\u6a21\u578b\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("sedimentation_ptmm.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
