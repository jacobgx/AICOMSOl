/*
 * beads_on_string.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:10 by COMSOL 6.3.0.290. */
public class beads_on_string {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Polymer_Flow_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("vef", "ViscoelasticFlow", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/vef", true);

    model.baseSystem("none");

    model.param().set("epsilon", "0.05");
    model.param().descr("epsilon", "\u534a\u5f84\u6270\u52a8");
    model.param().set("beta", "0.25");
    model.param().descr("beta", "\u6eb6\u5242\u9ecf\u5ea6\u6bd4");
    model.param().set("Oh", "3.16");
    model.param().descr("Oh", "Ohnesorge \u6570");
    model.param().set("De", "94.9");
    model.param().descr("De", "Deborah \u6570");
    model.param().set("mus", "beta*Oh");
    model.param().descr("mus", "\u6eb6\u5242\u9ecf\u5ea6");
    model.param().set("mup", "(1-beta)*Oh");
    model.param().descr("mup", "\u5f39\u6027\u9ecf\u5ea6");

    model.component("comp1").geom("geom1").create("pc1", "ParametricCurve");
    model.component("comp1").geom("geom1").feature("pc1").set("parmax", "8*pi");
    model.component("comp1").geom("geom1").feature("pc1").set("coord", new String[]{"1+epsilon*cos(s/2)", ""});
    model.component("comp1").geom("geom1").feature("pc1").setIndex("coord", "s", 1);
    model.component("comp1").geom("geom1").run("pc1");
    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1").set("type", "open");
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "1+epsilon", 0, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 0, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 1, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 1, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 2, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "8*pi", 2, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "1+epsilon", 3, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "8*pi", 3, 1);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("csol1", "ConvertToSolid");
    model.component("comp1").geom("geom1").feature("csol1").selection("input").set("pc1", "pol1");
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").common().create("free1", "DeformingDomain");
    model.component("comp1").common("free1").selection().all();

    model.component("comp1").geom("geom1").run();

    model.component("comp1").common("free1").set("smoothingType", "hyperelastic");

    model.component("comp1").physics("vef").prop("ShapeProperty").set("order_fluid", 1);
    model.component("comp1").physics("vef").feature("fp1").set("rho_mat", "userdef");
    model.component("comp1").physics("vef").feature("fp1").set("rho", 1);
    model.component("comp1").physics("vef").feature("fp1").set("mu_s_mat", "userdef");
    model.component("comp1").physics("vef").feature("fp1").set("mu_s", "mus");
    model.component("comp1").physics("vef").feature("fp1").setIndex("mue", "mup", 0, 0);
    model.component("comp1").physics("vef").feature("fp1").setIndex("lambdae", "De", 0, 0);
    model.component("comp1").physics("vef").create("fs1", "FreeSurface", 1);
    model.component("comp1").physics("vef").feature("fs1").selection().set(4);
    model.component("comp1").physics("vef").feature("fs1").set("SurfaceTensionCoefficient", "userdef");
    model.component("comp1").physics("vef").feature("fs1").set("sigma", 1);
    model.component("comp1").physics("vef").feature("fs1").feature("cnta1").set("ConstrainNormalWallVelocity", true);
    model.component("comp1").physics("vef").create("pfc1", "PeriodicFlowCondition", 1);
    model.component("comp1").physics("vef").feature("pfc1").selection().set(2, 3);

    model.component("comp1").common().create("sym1", "Symmetry");
    model.component("comp1").common("sym1").selection().set(1, 2, 3);

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("size").set("table", "cfd");
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 9);
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", 0.001);
    model.component("comp1").mesh("mesh1").feature("size").set("hnarrow", 8);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tlist", "range(0,5,300)");
    model.study("std1").feature("time").set("usertol", true);
    model.study("std1").feature("time").set("rtol", 0.005);
    model.study("std1").feature("time").set("autoremesh", true);
    model.study("std1").createAutoSequences("sol");
    model.study("std1").createAutoSequences("jobs");

    model.sol().create("sol2");
    model.sol("sol2").label("\u91cd\u65b0\u5212\u5206\u7f51\u683c\u540e\u7684\u89e3 1");
    model.sol("sol2").study("std1");
    model.sol("sol1").feature("t1").feature("arDef").set("tadapsol", "sol2");
    model.sol("sol1").runFromTo("st1", "v1");

    model.result().dataset("dset2").set("geom", "geom1");
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u901f\u5ea6 (vef)");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").set("dataisaxisym", "off");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u538b\u529b (vef)");
    model.result("pg2").set("data", "dset2");
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
    model.result().dataset("rev1").set("data", "dset2");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u4e09\u7ef4\u901f\u5ea6 (vef)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").label("\u8868\u9762");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").label("\u52a8\u7f51\u683c");
    model.result("pg4").create("mesh1", "Mesh");
    model.result("pg4").feature("mesh1").set("meshdomain", "surface");
    model.result("pg4").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg4").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg4").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg4").feature("mesh1").create("sel1", "MeshSelection");
    model.result("pg4").feature("mesh1").feature("sel1").selection().set(1);
    model.result("pg4").feature("mesh1").set("qualmeasure", "custom");
    model.result("pg4").feature("mesh1").set("qualexpr", "comp1.spatial.relVol");
    model.result("pg4").feature("mesh1").set("colorrangeunitinterval", false);
    model.result("pg1").run();
    model.result().dataset().create("mir1", "Mirror2D");
    model.result().dataset("mir1").set("data", "dset2");
    model.result("pg1").run();
    model.result("pg1").set("data", "mir1");

    model.study("std1").feature("time").set("plot", true);
    model.study("std1").feature("time").set("plotfreq", "tsteps");

    model.sol("sol1").feature("t1").set("timemethod", "genalpha");
    model.sol("sol1").feature("t1").set("maxstepconstraintgenalpha", "expr");
    model.sol("sol1").feature("t1").set("maxstepexpressiongenalpha", "comp1.vef.dt_CFL");
    model.sol("sol1").feature("t1").feature("arDef").set("stopcondtype", "distortion");
    model.sol("sol1").feature("t1").feature("arDef").set("stopdistval", "1.05");
    model.sol("sol1").feature("t1").feature("arDef").set("solutionremesh", "tout");
    model.sol("sol1").feature("t1").feature("arDef").set("consistentremesh", true);
    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().duplicate("pg5", "pg1");
    model.result("pg5").run();
    model.result("pg5").label("\u5f62\u72b6");
    model.result("pg5").run();
    model.result("pg5").feature("surf1").set("expr", "1");
    model.result("pg5").feature("surf1").set("coloring", "uniform");
    model.result("pg5").feature("surf1").set("color", "black");
    model.result().numerical().create("min1", "MinLine");
    model.result().numerical("min1").selection().set(4);
    model.result().numerical("min1").setIndex("expr", "log10(r)", 0);
    model.result().numerical("min1").set("data", "dset2");
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u7ebf\u6700\u5c0f\u503c 1");
    model.result().numerical("min1").set("table", "tbl1");
    model.result().numerical("min1").setResult();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").set("data", "none");
    model.result("pg6").create("tblp1", "Table");
    model.result("pg6").feature("tblp1").set("source", "table");
    model.result("pg6").feature("tblp1").set("table", "tbl1");
    model.result("pg6").feature("tblp1").set("linewidth", "preference");
    model.result("pg6").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").label("\u6700\u5c0f\u534a\u5f84");
    model.result("pg6").set("xlabelactive", true);
    model.result("pg6").set("ylabelactive", true);
    model.result("pg6").set("xlabel", "t");
    model.result("pg6").run();
    model.result().dataset().create("grid1", "Grid1D");
    model.result().dataset("grid1").set("source", "data");
    model.result().dataset("grid1").set("parmin1", 50);
    model.result().dataset("grid1").set("parmax1", 300);
    model.result("pg6").run();
    model.result("pg6").create("fun1", "Function");
    model.result("pg6").feature("fun1").set("linewidth", "preference");
    model.result("pg6").feature("fun1").set("expr", "-1/(3*De*log(10))*x-0.3");
    model.result("pg6").feature("fun1").set("xdataexpr", "x");
    model.result("pg6").feature("fun1").set("lowerbound", 150);
    model.result("pg6").feature("fun1").set("upperbound", 250);
    model.result("pg6").feature("fun1").set("data", "grid1");
    model.result("pg6").feature("fun1").set("linestyle", "dashed");
    model.result("pg6").feature("fun1").set("linewidth", 2);
    model.result("pg6").feature("fun1").set("linecolor", "black");
    model.result("pg6").feature("fun1").set("legend", true);
    model.result("pg6").feature("fun1").set("legendmethod", "manual");
    model.result("pg6").feature("fun1").setIndex("legends", "slope= -1/(3*De*log(10))", 0);
    model.result("pg6").feature("fun1").set("titletype", "none");
    model.result("pg6").run();
    model.result("pg3").run();

    model.title("\u9ecf\u5f39\u6027\u7ec6\u4e1d\u7684\u4e32\u73e0\u7ed3\u6784");

    model
         .description("\u672c\u4f8b\u91c7\u7528 Oldroyd-B \u6d41\u4f53\u6765\u6a21\u62df\u9ecf\u5f39\u6027\u7ec6\u4e1d\u5728\u8868\u9762\u5f20\u529b\u4f5c\u7528\u4e0b\u7684\u7ec6\u5316\u8fc7\u7a0b\u3002\u5728\u65f6\u95f4\u5c0f\u4e8e\u805a\u5408\u7269\u5f1b\u8c6b\u65f6\u95f4\u7684\u60c5\u51b5\u4e0b\uff0c\u7ec6\u4e1d\u4f1a\u5f62\u6210\u4e32\u73e0\u7ed3\u6784\u3002\u5728\u8fdc\u8fdc\u8d85\u8fc7\u5f1b\u8c6b\u65f6\u95f4\u7684\u60c5\u51b5\u4e0b\uff0c\u6eb6\u6db2\u7531\u51e0\u4e4e\u662f\u7403\u5f62\u7684\u6db2\u6ef4\uff08\u901a\u8fc7\u4ee5\u6307\u6570\u500d\u7387\u53d8\u7ec6\u7684\u7ebf\u8fde\u63a5\uff09\u7ec4\u6210\u3002\u8fd9\u4e24\u79cd\u77ac\u6001\u72b6\u6001\u4e0e\u5b9e\u9a8c\u6d4b\u91cf\u7ed3\u679c\u90fd\u8868\u73b0\u51fa\u826f\u597d\u7684\u4e00\u81f4\u6027\u3002");

    model.label("beads_on_string.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
