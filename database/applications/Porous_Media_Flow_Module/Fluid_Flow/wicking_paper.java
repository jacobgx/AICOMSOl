/*
 * wicking_paper.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:12 by COMSOL 6.3.0.290. */
public class wicking_paper {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Porous_Media_Flow_Module\\Fluid_Flow");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("phtr", "PhaseTransportPorous", "geom1");
    model.component("comp1").physics().create("dl", "PorousMediaFlowDarcy", "geom1");
    model.component("comp1").physics("dl").prop("ShapeProperty").set("order_pressure", "1");

    model.component("comp1").multiphysics().create("mfpm1", "MultiphaseFlowInPorousMedia", 2);
    model.component("comp1").multiphysics("mfpm1").set("multiphaseflow_physics", "phtr");
    model.component("comp1").multiphysics("mfpm1").set("darcyc_physics", "dl");
    model.component("comp1").multiphysics("mfpm1").selection().all();

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/phtr", true);
    model.study("std1").feature("time").setSolveFor("/physics/dl", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/mfpm1", true);

    model.param().set("L0", "12[cm]");
    model.param().descr("L0", "\u7eb8\u6761\u9ad8\u5ea6");
    model.param().set("W0", "L0/8");
    model.param().descr("W0", "\u7eb8\u6761\u5bbd\u5ea6");
    model.param().set("th", "1[mm]");
    model.param().descr("th", "\u7eb8\u6761\u539a\u5ea6");
    model.param().set("gamma", "0.0723[N/m]");
    model.param().descr("gamma", "\u8868\u9762\u5f20\u529b");
    model.param().set("theta", "0");
    model.param().descr("theta", "\u63a5\u89e6\u89d2");
    model.param().set("Rc", "8.8e-7[m]");
    model.param().descr("Rc", "\u5b54\u9699\u534a\u5f84");
    model.param().set("pec", "2*gamma*cos(theta)/Rc");
    model.param().descr("pec", "\u5165\u53e3\u6bdb\u7ec6\u538b\u529b");
    model.param().set("lp", "2");
    model.param().descr("lp", "\u5b54\u9699\u5927\u5c0f\u5206\u5e03\u6307\u6570");
    model.param().set("por", "0.6");
    model.param().descr("por", "\u5b54\u9699\u7387");
    model.param().set("K", "por/8*Rc^2");
    model.param().descr("K", "\u6e17\u900f\u7387");
    model.param().set("rho_air", "1[kg/m^3]");
    model.param().descr("rho_air", "\u7a7a\u6c14\u5bc6\u5ea6");
    model.param().set("rho_water", "1e3[kg/m^3]");
    model.param().descr("rho_water", "\u6c34\u5bc6\u5ea6");
    model.param().set("mu_air", "1.76e-5[Pa*s]");
    model.param().descr("mu_air", "\u7a7a\u6c14\u9ecf\u5ea6");
    model.param().set("mu_water", "0.001[Pa*s]");
    model.param().descr("mu_water", "\u6c34\u9ecf\u5ea6");

    model.component("comp1").func().create("step1", "Step");
    model.component("comp1").func("step1").set("location", 0.9);
    model.component("comp1").func("step1").set("from", 1);
    model.component("comp1").func("step1").set("to", 0);
    model.component("comp1").func("step1").set("smooth", 0.2);

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"W0", "L0"});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("pmat1", "PorousMedia");
    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u6c34");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u7a7a\u6c14");

    model.component("comp1").physics("dl").prop("PhysicalModelProperty").set("dz", "th");
    model.component("comp1").physics("dl").create("pr1", "Pressure", 1);
    model.component("comp1").physics("dl").feature("pr1").selection().set(2);
    model.component("comp1").physics("dl").create("pr2", "Pressure", 1);
    model.component("comp1").physics("dl").feature("pr2").selection().set(3);
    model.component("comp1").physics("dl").feature("pr2").set("p0", "-(phtr.pc_s2)*step1(s1)-rho_air*L0*g_const");
    model.component("comp1").physics("dl").feature("pr2").set("constraintType", "unidirectionalConstraint");
    model.component("comp1").physics("dl").feature("pr2").set("constraintOptions", "weakConstraints");
    model.component("comp1").physics("phtr").prop("GravityEffects").set("IncludeGravity", true);
    model.component("comp1").physics("phtr").feature("porous1").feature("fluid1")
         .set("capillarypressuremodel", "BrooksCorey");
    model.component("comp1").physics("phtr").feature("porous1").feature("fluid1").set("pec", "pec");
    model.component("comp1").physics("phtr").feature("porous1").feature("fluid1").set("lambdap", "lp");
    model.component("comp1").physics("phtr").feature("porous1").feature("fluid1").set("phaselist_s1", "mat1");
    model.component("comp1").physics("phtr").feature("porous1").feature("fluid1").set("phaselist_s2", "mat2");
    model.component("comp1").physics("phtr").feature("init1").setIndex("s0", 0.99, 1);
    model.component("comp1").physics("phtr").create("mf1", "MassFlux", 1);
    model.component("comp1").physics("phtr").feature("mf1").setIndex("phases", true, 1);
    model.component("comp1").physics("phtr").feature("mf1").selection().set(3);
    model.component("comp1").physics("phtr").feature("mf1").setIndex("q0", "p_lm[kg/m/s]/th", 1);

    model.component("comp1").material("pmat1").set("porosity", "por");
    model.component("comp1").material("pmat1").propertyGroup("def").set("hydraulicpermeability", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"rho_water"});
    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", new String[]{"mu_water"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", new String[]{"rho_air"});
    model.component("comp1").material("mat2").propertyGroup("def").set("dynamicviscosity", new String[]{"mu_air"});

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(2);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 1);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(1, 4);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("elemcount", 200);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("elemratio", 1000);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tlist", "range(0,10,600)");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("initialstepbdfactive", true);
    model.sol("sol1").feature("t1").set("initialstepbdf", "0.0001");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u4f53\u79ef\u5206\u6570 (phtr)");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u538b\u529b (dl)");
    model.result("pg2").set("titletype", "custom");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").label("\u8868\u9762");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("expr", "p");
    model.result("pg2").feature("surf1").set("colortable", "Rainbow");
    model.result("pg2").feature("surf1").set("colortabletrans", "none");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg2").feature().create("str1", "Streamline");
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("posmethod", "uniform");
    model.result("pg2").feature("str1").set("pointtype", "arrow");
    model.result("pg2").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg2").feature("str1").set("color", "gray");
    model.result("pg2").feature("str1").set("smooth", "internal");
    model.result("pg2").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("data", "parent");
    model.result("pg2").feature("str1").selection().geom("geom1", 1);
    model.result("pg2").feature("str1").selection().set(1, 2, 3, 4);
    model.result("pg1").run();
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").run();
    model.result("pg3").label("\u542b\u6c34\u9971\u548c\u5ea6\uff08\u5df2\u5206\u7ec4\uff09");
    model.result("pg3").setIndex("looplevel", 2, 0);
    model.result("pg3").set("titletype", "manual");
    model.result("pg3").set("title", "\u542b\u6c34\u9971\u548c\u5ea6");
    model.result("pg3").set("paramindicator", "");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("data", "dset1");
    model.result("pg3").feature("surf1").setIndex("looplevel", 2, 0);
    model.result("pg3").feature("surf1").set("rangecoloractive", true);
    model.result("pg3").feature("surf1").set("rangecolormax", 1);
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").set("plotarrayenable", true);
    model.result("pg3").set("relpadding", 0.5);
    model.result("pg3").feature("surf1").set("arraydim", "1");
    model.result("pg3").run();
    model.result("pg3").feature().duplicate("surf2", "surf1");
    model.result("pg3").feature("surf2").set("arraydim", "1");
    model.result("pg3").run();
    model.result("pg3").feature("surf2").setIndex("looplevel", 5, 0);
    model.result("pg3").feature("surf2").set("inheritplot", "surf1");
    model.result("pg3").feature().duplicate("surf3", "surf2");
    model.result("pg3").feature("surf3").set("arraydim", "1");
    model.result("pg3").run();
    model.result("pg3").feature("surf3").setIndex("looplevel", 11, 0);
    model.result("pg3").feature().duplicate("surf4", "surf3");
    model.result("pg3").feature("surf4").set("arraydim", "1");
    model.result("pg3").run();
    model.result("pg3").feature("surf4").setIndex("looplevel", 21, 0);
    model.result("pg3").feature().duplicate("surf5", "surf4");
    model.result("pg3").feature("surf5").set("arraydim", "1");
    model.result("pg3").run();
    model.result("pg3").feature("surf5").setIndex("looplevel", 31, 0);
    model.result("pg3").feature().duplicate("surf6", "surf5");
    model.result("pg3").feature("surf6").set("arraydim", "1");
    model.result("pg3").run();
    model.result("pg3").feature("surf6").setIndex("looplevel", 41, 0);
    model.result("pg3").run();
    model.result("pg3").create("tlan1", "TableAnnotation");
    model.result("pg3").feature("tlan1").set("arraydim", "1");
    model.result("pg3").feature("tlan1").set("source", "localtable");
    model.result("pg3").feature("tlan1").setIndex("localtablematrix", "W0/2", 0, 0);
    model.result("pg3").feature("tlan1").setIndex("localtablematrix", 0, 0, 1);
    model.result("pg3").feature("tlan1").setIndex("localtablematrix", "t=10s", 0, 2);
    model.result("pg3").feature("tlan1").setIndex("localtablematrix", "2*W0", 1, 0);
    model.result("pg3").feature("tlan1").setIndex("localtablematrix", 0, 1, 1);
    model.result("pg3").feature("tlan1").setIndex("localtablematrix", "t=40s", 1, 2);
    model.result("pg3").feature("tlan1").setIndex("localtablematrix", "3*W0+W0/2", 2, 0);
    model.result("pg3").feature("tlan1").setIndex("localtablematrix", 0, 2, 1);
    model.result("pg3").feature("tlan1").setIndex("localtablematrix", "t=100s", 2, 2);
    model.result("pg3").feature("tlan1").setIndex("localtablematrix", "5*W0", 3, 0);
    model.result("pg3").feature("tlan1").setIndex("localtablematrix", 0, 3, 1);
    model.result("pg3").feature("tlan1").setIndex("localtablematrix", "t=200s", 3, 2);
    model.result("pg3").feature("tlan1").setIndex("localtablematrix", "6.5*W0", 4, 0);
    model.result("pg3").feature("tlan1").setIndex("localtablematrix", 0, 4, 1);
    model.result("pg3").feature("tlan1").setIndex("localtablematrix", "t=300s", 4, 2);
    model.result("pg3").feature("tlan1").setIndex("localtablematrix", "8*W0", 5, 0);
    model.result("pg3").feature("tlan1").setIndex("localtablematrix", 0, 5, 1);
    model.result("pg3").feature("tlan1").setIndex("localtablematrix", "t=400s", 5, 2);
    model.result("pg3").feature("tlan1").set("showpoint", false);
    model.result("pg3").feature("tlan1").set("anchorpoint", "uppermiddle");
    model.result("pg3").run();
    model.result().dataset().create("extr1", "Extrude2D");
    model.result().dataset("extr1").set("zmax", "th");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 21, 0);
    model.result("pg4").set("titletype", "manual");
    model.result("pg4").set("title", "\u542b\u6c34\u9971\u548c\u5ea6");
    model.result("pg4").set("paramindicator", "Time=eval(t) s");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").run();
    model.result("pg4").label("\u4e09\u7ef4\u542b\u6c34\u9971\u548c\u5ea6");
    model.result("pg4").set("view", "new");
    model.result("pg4").run();

    model.variable().create("var1");
    model.variable("var1").set("H_lf", "sqrt(gamma*Rc*cos(theta)*t/(2*mu_water))");
    model.variable("var1").descr("H_lf", "\u6db2\u4f53\u524d\u6cbf\u9ad8\u5ea6");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().set(1);

    model.sol("sol1").updateSolution();

    model.result("pg1").run();
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").setIndex("expr", "intop1(s1)*th*rho_water*por", 0);
    model.result().numerical("gev1").setIndex("unit", "g", 0);
    model.result().numerical("gev1").setIndex("descr", "\u5438\u6536\u7684\u6c34\uff0c\u4eff\u771f\u7ed3\u679c", 0);
    model.result().numerical("gev1").setIndex("expr", "min(L0,H_lf)*W0*th*rho_water*por", 1);
    model.result().numerical("gev1").setIndex("unit", "g", 1);
    model.result().numerical("gev1")
         .setIndex("descr", "\u5438\u6536\u7684\u6c34\uff0cLucas-Washburn \u65b9\u7a0b\u7ed9\u51fa\u7684\u7ed3\u679c", 1);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u5168\u5c40\u8ba1\u7b97 1");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").set("data", "none");
    model.result("pg5").create("tblp1", "Table");
    model.result("pg5").feature("tblp1").set("source", "table");
    model.result("pg5").feature("tblp1").set("table", "tbl1");
    model.result("pg5").feature("tblp1").set("linewidth", "preference");
    model.result("pg5").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").label("\u5438\u6c34\u91cf");
    model.result("pg5").run();
    model.result("pg5").feature("tblp1").set("linestyle", "cycle");
    model.result("pg5").feature("tblp1").set("linecolor", "black");
    model.result("pg5").feature("tblp1").set("linewidth", 2);
    model.result("pg5").feature("tblp1").set("legend", true);
    model.result("pg5").feature("tblp1").set("legendmethod", "manual");
    model.result("pg5").feature("tblp1").setIndex("legends", "\u4eff\u771f\u7ed3\u679c", 0);
    model.result("pg5").feature("tblp1")
         .setIndex("legends", "Lucas-Washburn \u65b9\u7a0b\u7ed9\u51fa\u7684\u7ed3\u679c", 1);
    model.result("pg5").run();
    model.result("pg5").set("legendpos", "upperleft");
    model.result("pg5").run();
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("ylabel", "\u5438\u6536\u7684\u6c34 (g)");

    model.title("\u7eb8\u6761\u82af\u5438");

    model
         .description("\u672c\u4f8b\u6a21\u62df\u591a\u5b54\u4ecb\u8d28\u4e2d\u7684\u82af\u5438\u4f5c\u7528\u3002\u5f53\u5e72\u71e5\u7684\u591a\u5b54\u4ecb\u8d28\u4e0e\u6d41\u4f53\u63a5\u89e6\u65f6\uff0c\u4f1a\u53d1\u751f\u82af\u5438\u73b0\u8c61\uff1a\u7531\u4e8e\u6bdb\u7ec6\u529b\uff0c\u591a\u5b54\u4ecb\u8d28\u4f1a\u5438\u6536\u6d41\u4f53\uff0c\u76f4\u5230\u91cd\u529b\u4e0e\u6bdb\u7ec6\u529b\u8fbe\u5230\u5e73\u8861\u3002");

    model.label("wicking_paper.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
