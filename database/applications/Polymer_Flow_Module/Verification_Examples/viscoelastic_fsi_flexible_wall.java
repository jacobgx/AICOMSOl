/*
 * viscoelastic_fsi_flexible_wall.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:10 by COMSOL 6.3.0.290. */
public class viscoelastic_fsi_flexible_wall {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Polymer_Flow_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("vef", "ViscoelasticFlow", "geom1");
    model.component("comp1").physics("vef").prop("AdvancedSettingProperty").set("UsePseudoTime", "1");
    model.component("comp1").physics("vef").field("velocity").field("u_fluid");
    model.component("comp1").physics("vef").field("velocity")
         .component(new String[]{"u_fluid", "v_fluid", "w_fluid"});
    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");
    model.component("comp1").physics("solid").field("displacement").field("u_solid");
    model.component("comp1").physics("solid").field("displacement")
         .component(new String[]{"u_solid", "v_solid", "w_solid"});
    model.component("comp1").physics("solid").prop("ShapeProperty").set("order_displacement", "2");

    model.component("comp1").multiphysics().create("fsi1", "FluidStructureInteractionBC", 1);
    model.component("comp1").multiphysics("fsi1").set("Fluid_physics", "vef");
    model.component("comp1").multiphysics("fsi1").set("Structure_physics", "solid");
    model.component("comp1").multiphysics("fsi1").selection().all();

    model.component("comp1").common().create("free1", "DeformingDomain");
    model.component("comp1").common("free1").set("smoothingType", "yeoh");
    model.component("comp1").common("free1").selection().set();

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/vef", true);
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/fsi1", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("Uin", "0.03[m/s]", "\u5165\u53e3\u901f\u5ea6");
    model.param().set("D", "0.01[m]", "\u901a\u9053\u9ad8\u5ea6");
    model.param().set("rho_f", "1000[kg/m^3]", "\u6d41\u4f53\u5bc6\u5ea6");
    model.param().set("E_s", "35.9[kPa]", "\u6768\u6c0f\u6a21\u91cf");
    model.param().set("nu_s", "0.45", "\u6cca\u677e\u6bd4");
    model.param().set("p_ext", "1.755[Pa]", "\u5916\u90e8\u538b\u529b");
    model.param().set("p_out", "0[Pa]", "\u51fa\u53e3\u538b\u529b");
    model.param().set("b_fact", "0.01", "\u58c1\u539a\u56e0\u5b50");
    model.param().set("b", "b_fact*D", "\u58c1\u539a");
    model.param().set("Lu", "5*D", "\u5f39\u6027\u58c1\u4e0a\u4fa7\u957f\u5ea6");
    model.param().set("Ld", "30*D", "\u5f39\u6027\u58c1\u4e0b\u4fa7\u957f\u5ea6");
    model.param().set("Lw", "5*D", "\u5f39\u6027\u58c1\u957f\u5ea6");
    model.param().set("L", "Lu+Lw+Ld", "\u603b\u957f\u5ea6");
    model.param().set("mu_s", "5e-4[Pa*s]", "\u6eb6\u5242\u9ecf\u5ea6");
    model.param().set("mu_p", "5e-4[Pa*s]", "\u805a\u5408\u7269\u9ecf\u5ea6");
    model.param().set("rho_s", "1000[kg/m^3]", "\u56fa\u4f53\u5bc6\u5ea6");
    model.param().set("lambda", "Wi*D/Uin", "\u5f1b\u8c6b\u65f6\u95f4");
    model.param().set("Wi", "0.05", "\u9b4f\u68ee\u8d1d\u683c\u6570");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"L", "D"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"1", "b"});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"0", "D"});
    model.component("comp1").geom("geom1").feature("r2").set("layerbottom", false);
    model.component("comp1").geom("geom1").feature("r2").set("layerleft", true);
    model.component("comp1").geom("geom1").feature("r2").setIndex("layer", "Lu", 0);
    model.component("comp1").geom("geom1").feature("r2").setIndex("layer", "Lw", 1);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").common("free1").selection().set(1);

    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"L", "b"});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("vef").selection().set(1);
    model.component("comp1").physics("vef").feature("fp1").set("rho_mat", "userdef");
    model.component("comp1").physics("vef").feature("fp1").set("rho", "rho_f");
    model.component("comp1").physics("vef").feature("fp1").set("mu_s_mat", "userdef");
    model.component("comp1").physics("vef").feature("fp1").set("mu_s", "mu_s");
    model.component("comp1").physics("vef").feature("fp1").setIndex("mue", "mu_p", 0, 0);
    model.component("comp1").physics("vef").feature("fp1").setIndex("lambdae", "lambda", 0, 0);
    model.component("comp1").physics("vef").create("inl1", "InletBoundary", 1);
    model.component("comp1").physics("vef").feature("inl1").selection().set(1);
    model.component("comp1").physics("vef").feature("inl1").set("BoundaryCondition", "FullyDevelopedFlow");
    model.component("comp1").physics("vef").feature("inl1").set("Uavfdf", "Uin");
    model.component("comp1").physics("vef").create("out1", "OutletBoundary", 1);
    model.component("comp1").physics("vef").feature("out1").selection().set(12);
    model.component("comp1").physics("solid").feature("lemm1").set("E_mat", "userdef");
    model.component("comp1").physics("solid").feature("lemm1").set("E", "E_s");
    model.component("comp1").physics("solid").feature("lemm1").set("nu_mat", "userdef");
    model.component("comp1").physics("solid").feature("lemm1").set("nu", "nu_s");
    model.component("comp1").physics("solid").feature("lemm1").set("rho_mat", "userdef");
    model.component("comp1").physics("solid").feature("lemm1").set("rho", "rho_s");
    model.component("comp1").physics("solid").create("fix1", "Fixed", 1);
    model.component("comp1").physics("solid").feature("fix1").selection().set(3, 4, 10, 13);
    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 1);
    model.component("comp1").physics("solid").feature("bndl1").selection().set(8);
    model.component("comp1").physics("solid").feature("bndl1")
         .set("forceReferenceArea", new String[]{"0", "-p_ext*1[m]", "0"});

    model.component("comp1").mesh("mesh1").autoMeshSize(4);
    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature().remove("ftri1");
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(6);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 4);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("elemcount", 30);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("elemratio", 10);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("symmetric", true);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").setSolveFor("/physics/solid", false);
    model.study("std1").feature("stat").setSolveFor("/frame/spatial1", false);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/fsi1", false);
    model.study("std1").create("stat2", "Stationary");
    model.study("std1").feature("stat2").setSolveFor("/physics/vef", false);
    model.study("std1").create("stat3", "Stationary");
    model.study("std1").feature("stat3").set("useparam", true);
    model.study("std1").feature("stat3").setIndex("pname", "Uin", 0);
    model.study("std1").feature("stat3").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat3").setIndex("punit", "m/s", 0);
    model.study("std1").feature("stat3").setIndex("pname", "Uin", 0);
    model.study("std1").feature("stat3").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat3").setIndex("punit", "m/s", 0);
    model.study("std1").feature("stat3").setIndex("pname", "Wi", 0);
    model.study("std1").feature("stat3").setIndex("plistarr", "0.15 0.3 0.6", 0);
    model.study("std1").createAutoSequences("sol");
    model.study("std1").createAutoSequences("jobs");

    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u901f\u5ea6 (vef)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u538b\u529b (vef)");
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
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").label("\u5e94\u529b (solid)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg3").feature("surf1").set("threshold", "manual");
    model.result("pg3").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg3").feature("surf1").set("colortable", "Rainbow");
    model.result("pg3").feature("surf1").set("colortabletrans", "none");
    model.result("pg3").feature("surf1").set("colorscalemode", "linear");
    model.result("pg3").feature("surf1").set("resolution", "normal");
    model.result("pg3").feature("surf1").set("colortable", "Prism");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").label("\u52a8\u7f51\u683c");
    model.result("pg4").create("mesh1", "Mesh");
    model.result("pg4").feature("mesh1").set("meshdomain", "surface");
    model.result("pg4").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg4").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg4").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg4").feature("mesh1").create("sel1", "MeshSelection");
    model.result("pg4").feature("mesh1").feature("sel1").selection().set(1, 2, 3, 4);
    model.result("pg4").feature("mesh1").set("qualmeasure", "custom");
    model.result("pg4").feature("mesh1").set("qualexpr", "comp1.spatial.relVol");
    model.result("pg4").feature("mesh1").set("colorrangeunitinterval", false);
    model.result("pg1").run();

    model.study("std1").showAutoSequences("all");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u677f\u53d8\u5f62");
    model.result("pg5").create("lngr1", "LineGraph");
    model.result("pg5").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg5").feature("lngr1").set("linewidth", "preference");
    model.result("pg5").feature("lngr1").selection().set(7);
    model.result("pg5").feature("lngr1").set("expr", "v_solid");
    model.result("pg5").feature("lngr1").set("xdata", "expr");
    model.result("pg5").feature("lngr1").set("xdataexpr", "x");
    model.result("pg5").feature("lngr1").set("legend", true);
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("\u677f\u4e0a\u7684\u538b\u529b");
    model.result("pg6").create("lngr1", "LineGraph");
    model.result("pg6").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg6").feature("lngr1").set("linewidth", "preference");
    model.result("pg6").feature("lngr1").selection().set(7);
    model.result("pg6").feature("lngr1").set("expr", "p");
    model.result("pg6").feature("lngr1").set("xdata", "expr");
    model.result("pg6").feature("lngr1").set("xdataexpr", "x");
    model.result("pg6").feature("lngr1").set("legend", true);
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").set("data", "dset1");
    model.result("pg7").setIndex("looplevel", 3, 0);
    model.result("pg7").label("\u8fb9\u754c\u8f7d\u8377 (solid)");
    model.result("pg7").set("showlegends", true);
    model.result("pg7").set("titletype", "label");
    model.result("pg7").set("frametype", "spatial");
    model.result("pg7").set("showlegendsunit", true);
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", new String[]{"1"});
    model.result("pg7").feature("surf1").label("\u7070\u8272\u8868\u9762");
    model.result("pg7").feature("surf1").set("coloring", "uniform");
    model.result("pg7").feature("surf1").set("color", "gray");
    model.result("pg7").feature("surf1").set("inheritcolor", false);
    model.result("pg7").feature("surf1").set("inheritrange", false);
    model.result("pg7").feature("surf1").set("inheritheightscale", false);
    model.result("pg7").feature("surf1").create("sel1", "Selection");
    model.result("pg7").feature("surf1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg7").feature("surf1").feature("sel1").selection().set(2, 3, 4);
    model.result("pg7").create("arwl1", "ArrowLine");
    model.result("pg7").feature("arwl1").set("expr", new String[]{"solid.bndl1.fax", "solid.bndl1.fay"});
    model.result("pg7").feature("arwl1").set("placement", "gausspoints");
    model.result("pg7").feature("arwl1").set("arrowbase", "tail");
    model.result("pg7").feature("arwl1").label("\u8fb9\u754c\u8f7d\u8377 1");
    model.result("pg7").feature("arwl1").set("inheritplot", "none");
    model.result("pg7").feature("arwl1").create("col", "Color");
    model.result("pg7").feature("arwl1").feature("col").set("colortable", "Rainbow");
    model.result("pg7").feature("arwl1").feature("col").set("colortabletrans", "none");
    model.result("pg7").feature("arwl1").feature("col").set("colorscalemode", "linear");
    model.result("pg7").feature("arwl1").feature("col").set("colordata", "arrowlength");
    model.result("pg7").feature("arwl1").feature("col").set("coloring", "gradient");
    model.result("pg7").feature("arwl1").feature("col").set("topcolor", "red");
    model.result("pg7").feature("arwl1").feature("col").set("bottomcolor", "custom");
    model.result("pg7").feature("arwl1").feature("col")
         .set("custombottomcolor", new double[]{0.5882353186607361, 0.5137255191802979, 0.5176470875740051});
    model.result("pg7").feature("arwl1").set("color", "red");
    model.result("pg7").feature().move("surf1", 1);
    model.result("pg7").create("arwl2", "ArrowLine");
    model.result("pg7").feature("arwl2").set("expr", new String[]{"fsi1.F_Atx", "fsi1.F_Aty"});
    model.result("pg7").feature("arwl2").set("placement", "gausspoints");
    model.result("pg7").feature("arwl2").set("arrowbase", "tail");
    model.result("pg7").feature("arwl2").label("\u6d41-\u56fa\u8026\u5408 1 (\u5207\u5411\u8f7d\u8377)");
    model.result("pg7").feature("arwl2").set("inheritplot", "arwl1");
    model.result("pg7").feature("arwl2").create("col", "Color");
    model.result("pg7").feature("arwl2").feature("col").set("colortable", "Rainbow");
    model.result("pg7").feature("arwl2").feature("col").set("colortabletrans", "none");
    model.result("pg7").feature("arwl2").feature("col").set("colorscalemode", "linear");
    model.result("pg7").feature("arwl2").feature("col").set("colordata", "arrowlength");
    model.result("pg7").feature("arwl2").feature("col").set("coloring", "gradient");
    model.result("pg7").feature("arwl2").feature("col").set("topcolor", "red");
    model.result("pg7").feature("arwl2").feature("col").set("bottomcolor", "custom");
    model.result("pg7").feature("arwl2").feature("col")
         .set("custombottomcolor", new double[]{0.5882353186607361, 0.5137255191802979, 0.5176470875740051});
    model.result("pg7").feature("arwl2").set("color", "red");
    model.result("pg7").feature().move("surf1", 2);
    model.result("pg7").create("arwl3", "ArrowLine");
    model.result("pg7").feature("arwl3").set("expr", new String[]{"fsi1.F_Anx", "fsi1.F_Any"});
    model.result("pg7").feature("arwl3").set("placement", "gausspoints");
    model.result("pg7").feature("arwl3").set("arrowbase", "tail");
    model.result("pg7").feature("arwl3").label("\u6d41-\u56fa\u8026\u5408 1 (\u6cd5\u5411\u8f7d\u8377)");
    model.result("pg7").feature("arwl3").set("inheritplot", "arwl2");
    model.result("pg7").feature("arwl3").create("col", "Color");
    model.result("pg7").feature("arwl3").feature("col").set("colortable", "Rainbow");
    model.result("pg7").feature("arwl3").feature("col").set("colortabletrans", "none");
    model.result("pg7").feature("arwl3").feature("col").set("colorscalemode", "linear");
    model.result("pg7").feature("arwl3").feature("col").set("colordata", "arrowlength");
    model.result("pg7").feature("arwl3").feature("col").set("coloring", "gradient");
    model.result("pg7").feature("arwl3").feature("col").set("topcolor", "red");
    model.result("pg7").feature("arwl3").feature("col").set("bottomcolor", "custom");
    model.result("pg7").feature("arwl3").feature("col")
         .set("custombottomcolor", new double[]{0.5882353186607361, 0.5137255191802979, 0.5176470875740051});
    model.result("pg7").feature("arwl3").set("color", "red");
    model.result("pg7").feature().move("surf1", 3);
    model.result("pg7").label("\u8fb9\u754c\u8f7d\u8377 (solid)");

    model.nodeGroup().create("dset1solidlgrp", "Results");
    model.nodeGroup("dset1solidlgrp").label("\u5916\u52a0\u8f7d\u8377 (solid)");
    model.nodeGroup("dset1solidlgrp").set("type", "plotgroup");
    model.nodeGroup("dset1solidlgrp").placeAfter("plotgroup", "pg6");
    model.nodeGroup("dset1solidlgrp").label("\u5916\u52a0\u8f7d\u8377 (solid)");
    model.nodeGroup("dset1solidlgrp").placeAfter("plotgroup", "pg7");
    model.nodeGroup("dset1solidlgrp").add("plotgroup", "pg7");

    model.result("pg7").run();
    model.result("pg7").run();

    model.nodeGroup().remove("dset1solidlgrp");

    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg6").run();

    model.title("\u901a\u8fc7\u67d4\u6027\u58c1\u901a\u9053\u7684\u9ecf\u5f39\u6027\u6d41\u52a8");

    model
         .description("\u672c\u6a21\u578b\u8868\u793a\u4e00\u4e2a\u5f39\u6027\u7ba1\u4e2d\u7684\u6d41\u52a8\u6d4b\u8bd5\u6848\u4f8b\uff0c\u8fd9\u4e2a\u7279\u5b9a\u7684\u6848\u4f8b\u5bf9\u9ecf\u5f39\u6027\u6d41\u52a8\u8fdb\u884c\u5efa\u6a21\uff0c\u5e76\u8868\u5f81\u4e86\u90e8\u5206\u4e0a\u58c1\u88ab\u4e00\u4e2a\u53d7\u5916\u538b\u7684\u5f39\u6027\u677f\u6240\u53d6\u4ee3\u7684\u901a\u9053\u4e2d\u7684\u7a33\u5b9a\u6d41\u52a8\u3002\u8be5\u6a21\u578b\u53ef\u4ee5\u91cd\u73b0\u6587\u732e\u4e2d\u62a5\u544a\u7684\u7ed3\u679c\u3002\n\n\u6ce8\uff1a\u8be5\u6a21\u578b\u9700\u8981\u201c\u805a\u5408\u7269\u6d41\u52a8\u6a21\u5757\u201d\u548c\u201c\u7ed3\u6784\u529b\u5b66\u6a21\u5757\u201d\u3002");

    model.label("viscoelastic_fsi_flexible_wall.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
