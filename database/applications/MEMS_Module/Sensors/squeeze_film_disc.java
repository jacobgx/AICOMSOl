/*
 * squeeze_film_disc.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:05 by COMSOL 6.3.0.290. */
public class squeeze_film_disc {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\MEMS_Module\\Sensors");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("tff", "ThinFilmFlowEdge", "geom1");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").setSolveFor("/physics/tff", true);

    model.param().set("r0", "1[mm]");
    model.param().descr("r0", "\u5706\u76d8\u534a\u5f84");
    model.param().set("h0", "10[um]");
    model.param().descr("h0", "\u95f4\u9699\u9ad8\u5ea6");
    model.param().set("dh", "dhND*h0");
    model.param().descr("dh", "\u95f4\u9699\u9ad8\u5ea6\u53d8\u5316");
    model.param().set("dhND", "0.01");
    model.param().descr("dhND", "\u95f4\u9699\u9ad8\u5ea6\u53d8\u5316\u7684\u6bd4\u4f8b");
    model.param().set("mu0", "1e-5[Pa*s]");
    model.param().descr("mu0", "\u6c14\u4f53\u9ecf\u5ea6");
    model.param().set("f0", "1000[Hz]");
    model.param().descr("f0", "\u632f\u52a8\u9891\u7387");

    model.component("comp1").geom("geom1").create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls1").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord2", new String[]{"r0", "0"});
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").cpl().create("intop1", "Integration");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop1").selection().set(1);

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("Ftot", "intop1(tff.fwallz)");
    model.component("comp1").variable("var1").descr("Ftot", "\u5706\u76d8\u4e0a\u7684\u603b\u529b");
    model.component("comp1").variable("var1").set("Ftotan", "-3*pi*mu0*vf*r0^4/(2*h0^3)");
    model.component("comp1").variable("var1").descr("Ftotan", "\u89e3\u6790\u5f0f");
    model.component("comp1").variable("var1").set("vf", "2*pi*f0*dh");
    model.component("comp1").variable("var1").descr("vf", "\u5706\u76d8\u901f\u5ea6");
    model.component("comp1").variable("var1")
         .set("Ftotantime", "-6*pi^2*mu0*f0*r0^4*dh*cos(2*pi*f0*t)/(2*(h0+dh*sin(2*pi*f0*t))^3)");
    model.component("comp1").variable("var1").descr("Ftotantime", "\u89e3\u6790\u5f0f");

    model.component("comp1").func().create("an1", "Analytic");
    model.component("comp1").func("an1").set("funcname", "Pan");
    model.component("comp1").func("an1").set("expr", "-6*pi*mu0*f0*dh*(r0^2-rf^2)/h0^3");
    model.component("comp1").func("an1").set("args", "rf");
    model.component("comp1").func("an1").setIndex("argunit", "m", 0);
    model.component("comp1").func("an1").set("fununit", "Pa");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", new String[]{"mu0"});

    model.component("comp1").physics("tff").prop("EquationType").set("EquationType", "ModifiedReynoldsEquation");
    model.component("comp1").physics("tff").feature("ffp1").set("WallNormal", "InverseOrientation");
    model.component("comp1").physics("tff").feature("ffp1").set("hw1", "h0");
    model.component("comp1").physics("tff").feature("ffp1").set("uw_src", "Off");
    model.component("comp1").physics("tff").feature("ffp1").set("TangentialWallVelocity", "userdef");
    model.component("comp1").physics("tff").feature("ffp1").set("vw", new String[]{"0", "0", "vf"});
    model.component("comp1").physics("tff").feature().duplicate("ffp2", "ffp1");
    model.component("comp1").physics("tff").feature("ffp2").set("uw_src", "userdef");
    model.component("comp1").physics("tff").feature("ffp2").set("uw", new String[]{"0", "0", "dh*sin(2*pi*f0*t)"});
    model.component("comp1").physics("tff").feature("ffp2").set("TangentialWallVelocity", "FromDeformation");

    model.component("comp1").mesh("mesh1").autoMeshSize(2);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("freq").set("plist", 1000);
    model.study("std1").feature("freq").set("useadvanceddisable", true);
    model.study("std1").feature("freq").set("disabledphysics", new String[]{"tff/ffp2"});
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").label("Revolution 2D");
    model.result().dataset("rev1").set("data", "dset1");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u6d41\u4f53\u538b\u529b (tff)");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("xlabel", "\u5f84\u5411\u8ddd\u79bb (m)");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "\u538b\u529b (Pa)");
    model.result("pg2").set("legendpos", "upperleft");
    model.result("pg2").create("lngr1", "LineGraph");
    model.result("pg2").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg2").feature("lngr1").set("linewidth", "preference");
    model.result("pg2").feature("lngr1").selection().set(1);
    model.result("pg2").feature("lngr1").set("legend", true);
    model.result("pg2").feature("lngr1").set("legendmethod", "manual");
    model.result("pg2").feature("lngr1").setIndex("legends", "COMSOL", 0);
    model.result("pg2").run();
    model.result("pg2").create("lngr2", "LineGraph");
    model.result("pg2").feature("lngr2").set("markerpos", "datapoints");
    model.result("pg2").feature("lngr2").set("linewidth", "preference");
    model.result("pg2").feature("lngr2").selection().set(1);
    model.result("pg2").feature("lngr2").set("expr", "Pan(r)");
    model.result("pg2").feature("lngr2").set("linestyle", "none");
    model.result("pg2").feature("lngr2").set("linemarker", "cycle");
    model.result("pg2").feature("lngr2").set("markerpos", "interp");
    model.result("pg2").feature("lngr2").set("legend", true);
    model.result("pg2").feature("lngr2").set("legendmethod", "manual");
    model.result("pg2").feature("lngr2").setIndex("legends", "\u89e3\u6790\u89e3", 0);
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").label("\u5f84\u5411\u538b\u529b");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").run();
    model.result("pg3").create("arwl1", "ArrowLine");
    model.result("pg3").feature("arwl1").set("expr", new String[]{"tff.fwallr", "tff.fwallz"});
    model.result("pg3").feature("arwl1").set("descr", "\u58c1\u4e0a\u6d41\u4f53\u8f7d\u8377");
    model.result("pg3").feature("arwl1").set("arrowcount", 30);
    model.result("pg3").feature("arwl1").set("scaleactive", true);
    model.result("pg3").feature("arwl1").set("scale", "2.2e-6");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").create("line1", "Line");
    model.result("pg3").feature("line1").set("expr", "tff.fwallz");
    model.result("pg3").feature("line1").set("descr", "\u58c1\u4e0a\u6d41\u4f53\u8f7d\u8377\uff0cz \u5206\u91cf");
    model.result("pg3").feature("line1").set("colortabletrans", "reverse");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").label("\u58c1\u8f7d\u8377");
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").setIndex("expr", "abs(Ftot)", 0);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u5168\u5c40\u8ba1\u7b97 1");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();
    model.result().numerical().create("gev2", "EvalGlobal");
    model.result().numerical("gev2").set("expr", new String[]{"Ftotan"});
    model.result().numerical("gev2").set("descr", new String[]{"\u89e3\u6790\u5f0f"});
    model.result().numerical("gev2").set("unit", new String[]{"N"});
    model.result().numerical("gev2").set("table", "tbl1");
    model.result().numerical("gev2").appendResult();

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/tff", true);
    model.study("std2").feature("time").set("tlist", "range(0,1/(40*f0),5/f0)");
    model.study("std2").feature("time").set("useparam", true);
    model.study("std2").feature("time").setIndex("pname", "r0", 0);
    model.study("std2").feature("time").setIndex("plistarr", "", 0);
    model.study("std2").feature("time").setIndex("punit", "m", 0);
    model.study("std2").feature("time").setIndex("pname", "r0", 0);
    model.study("std2").feature("time").setIndex("plistarr", "", 0);
    model.study("std2").feature("time").setIndex("punit", "m", 0);
    model.study("std2").feature("time").setIndex("pname", "dhND", 0);
    model.study("std2").feature("time").setIndex("plistarr", "0.01 0.1 0.2 0.3", 0);
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().dataset().create("rev2", "Revolve2D");
    model.result().dataset("rev2").label("Revolution 2D 1");
    model.result().dataset("rev2").set("data", "dset2");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("\u6d41\u4f53\u538b\u529b (tff) 1");
    model.result("pg4").set("data", "rev2");
    model.result("pg4").setIndex("looplevel", 201, 0);
    model.result("pg4").setIndex("looplevel", 4, 1);
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("smooth", "internal");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").set("data", "dset2");
    model.result("pg5").create("ptgr1", "PointGraph");
    model.result("pg5").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg5").feature("ptgr1").set("linewidth", "preference");
    model.result("pg5").feature("ptgr1").selection().set(1);
    model.result("pg5").feature("ptgr1").set("expr", "tff.hw");
    model.result("pg5").feature("ptgr1").set("descr", "\u53c2\u8003\u5e73\u9762\u4e0a\u7684\u58c1\u9ad8");
    model.result("pg5").run();
    model.result("pg5").feature("ptgr1").set("legend", true);
    model.result("pg5").run();
    model.result("pg5").set("axislimits", true);
    model.result("pg5").set("ymin", 0);
    model.result("pg5").set("legendpos", "lowerright");
    model.result("pg5").run();
    model.result("pg5").label("\u58c1\u9ad8\u5ea6 vs. \u65f6\u95f4");
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").set("data", "dset2");
    model.result("pg6").create("ptgr1", "PointGraph");
    model.result("pg6").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg6").feature("ptgr1").set("linewidth", "preference");
    model.result("pg6").feature("ptgr1").selection().set(1);
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").label("\u538b\u529b vs. \u65f6\u95f4");
    model.result("pg6").run();
    model.result().numerical().create("pev1", "EvalPoint");
    model.result().numerical("pev1").selection().set(1);
    model.result().numerical("pev1").setIndex("expr", "abs(pfilm)", 0);
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("\u70b9\u8ba1\u7b97 1");
    model.result().numerical("pev1").set("table", "tbl2");
    model.result().numerical("pev1").setResult();
    model.result().numerical().create("pev2", "EvalPoint");
    model.result().numerical("pev2").set("data", "dset2");
    model.result().numerical("pev2").setIndex("looplevelinput", "manual", 1);
    model.result().numerical("pev2").setIndex("looplevel", new int[]{1}, 1);
    model.result().numerical("pev2").selection().set(1);
    model.result().numerical("pev2").set("dataseries", "minimum");
    model.result().table().create("tbl3", "Table");
    model.result().table("tbl3").comments("\u70b9\u8ba1\u7b97 2");
    model.result().numerical("pev2").set("table", "tbl3");
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").set("data", "dset2");
    model.result("pg7").create("glob1", "Global");
    model.result("pg7").feature("glob1").set("markerpos", "datapoints");
    model.result("pg7").feature("glob1").set("linewidth", "preference");
    model.result("pg7").feature("glob1").set("expr", new String[]{"Ftot"});
    model.result("pg7").feature("glob1").set("descr", new String[]{"\u5706\u76d8\u4e0a\u7684\u603b\u529b"});
    model.result("pg7").feature("glob1").set("unit", new String[]{"N"});
    model.result("pg7").run();
    model.result("pg7").create("glob2", "Global");
    model.result("pg7").feature("glob2").set("markerpos", "datapoints");
    model.result("pg7").feature("glob2").set("linewidth", "preference");
    model.result("pg7").feature("glob2").set("expr", new String[]{"Ftotantime"});
    model.result("pg7").feature("glob2").set("descr", new String[]{"\u89e3\u6790\u5f0f"});
    model.result("pg7").feature("glob2").set("unit", new String[]{"N"});
    model.result("pg7").feature("glob2").set("linestyle", "dashed");
    model.result("pg7").run();
    model.result("pg7").set("axislimits", true);
    model.result("pg7").set("xmin", 0.003);
    model.result("pg7").set("xmax", 0.005);
    model.result("pg7").set("legendpos", "lowerleft");
    model.result("pg7").label("\u5706\u76d8\u4e0a\u7684\u603b\u529b vs. \u65f6\u95f4");
    model.result("pg7").run();
    model.result("pg1").run();

    model.title("\u632f\u52a8\u76d8\u538b\u819c\u6c14\u4f53\u963b\u5c3c");

    model
         .description("\u8fd9\u4e2a\u57fa\u51c6\u6a21\u578b\u8ba1\u7b97\u9891\u57df\u548c\u65f6\u57df\u4e2d\u4f5c\u7528\u5728\u632f\u52a8\u76d8\u4e0a\u7684\u603b\u529b\uff0c\u5e76\u5c06\u7ed3\u679c\u4e0e\u89e3\u6790\u89e3\u8fdb\u884c\u6bd4\u8f83\u3002\u5f53\u632f\u5e45\u8db3\u591f\u5c0f\u65f6\uff0c\u7cfb\u7edf\u5448\u7ebf\u6027\u53d8\u5316\uff0c\u9891\u57df\u548c\u65f6\u57df\u7ed3\u679c\u4e0e\u7406\u8bba\u503c\u975e\u5e38\u543b\u5408\u3002\u589e\u5927\u632f\u5e45\u4ea7\u751f\u975e\u7ebf\u6027\u54cd\u5e94\uff0c\u5728\u9891\u57df\u4e2d\u65e0\u6cd5\u6a21\u62df\u8fd9\u4e00\u54cd\u5e94\uff0c\u56e0\u6b64\u5728\u65f6\u57df\u4e0a\u8fdb\u884c\u4e86\u5206\u6790\uff0c\u5e76\u5c06\u7ed3\u679c\u4e0e\u89e3\u6790\u89e3\u8fdb\u884c\u4e86\u6bd4\u8f83\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("squeeze_film_disc.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
