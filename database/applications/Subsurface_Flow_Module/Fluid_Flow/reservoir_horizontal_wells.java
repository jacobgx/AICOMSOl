/*
 * reservoir_horizontal_wells.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:29 by COMSOL 6.3.0.290. */
public class reservoir_horizontal_wells {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Subsurface_Flow_Module\\Fluid_Flow");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("phtr", "PhaseTransportPorous", "geom1");
    model.component("comp1").physics("phtr").field("volumefraction").field("sw");
    model.component("comp1").physics("phtr").field("volumefraction").component(new String[]{"sw", "sn"});
    model.component("comp1").physics().create("dl", "PorousMediaFlowDarcy", "geom1");
    model.component("comp1").physics("dl").prop("ShapeProperty").set("order_pressure", "1");

    model.component("comp1").multiphysics().create("mfpm1", "MultiphaseFlowInPorousMedia", 3);
    model.component("comp1").multiphysics("mfpm1").set("multiphaseflow_physics", "phtr");
    model.component("comp1").multiphysics("mfpm1").set("darcyc_physics", "dl");
    model.component("comp1").multiphysics("mfpm1").selection().all();

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/phtr", true);
    model.study("std1").feature("time").setSolveFor("/physics/dl", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/mfpm1", true);

    model.component("comp1").geom("geom1").lengthUnit("ft");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new int[]{2700, 2700, 160});
    model.component("comp1").geom("geom1").feature("blk1").set("layerbottom", false);
    model.component("comp1").geom("geom1").feature("blk1").set("layertop", true);
    model.component("comp1").geom("geom1").feature("blk1").setIndex("layer", 20, 0);
    model.component("comp1").geom("geom1").feature("blk1").setIndex("layer", 20, 1);
    model.component("comp1").geom("geom1").feature("blk1").setIndex("layer", 20, 2);
    model.component("comp1").geom("geom1").feature("blk1").setIndex("layer", 20, 3);
    model.component("comp1").geom("geom1").feature("blk1").setIndex("layer", 30, 4);

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls1").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord1", new int[]{1350, 0, 25});
    model.component("comp1").geom("geom1").feature("ls1").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord2", new int[]{1350, 2700, 25});
    model.component("comp1").geom("geom1").run("ls1");
    model.component("comp1").geom("geom1").create("ls2", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls2").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls2").set("coord1", new int[]{1350, 300, 150});
    model.component("comp1").geom("geom1").feature("ls2").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls2").set("coord2", new int[]{1350, 1200, 150});
    model.component("comp1").geom("geom1").run("ls2");
    model.component("comp1").geom("geom1").create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", 1300, 0);
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", 150, 2);
    model.component("comp1").geom("geom1").run("pt1");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("mcv1", "MeshControlVertices");
    model.component("comp1").geom("geom1").feature("mcv1").selection("input").set("fin", 15);
    model.component("comp1").geom("geom1").run("mcv1");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("rhow", "0.9814[g/cm^3]", "\u6c34\u7684\u5bc6\u5ea6");
    model.param().set("rhoo", "0.8975[g/cm^3]", "\u6cb9\u7684\u5bc6\u5ea6");
    model.param().set("muw", "0.96[cP]", "\u6c34\u7684\u9ecf\u5ea6");
    model.param().set("muo", "0.954[cP]", "\u6cb9\u7684\u9ecf\u5ea6");
    model.param().set("STB", "0.159[m^3]", "\u50a8\u7f50\u6876\u4f53\u79ef");
    model.param().set("massflow", "3000*STB/1[d]*rhow", "\u6cf5\u901f");

    model.func().create("int1", "Interpolation");
    model.func("int1")
         .set("table", new String[][]{{"0.22", "0"}, 
         {"0.3", "0.07"}, 
         {"0.4", "0.15"}, 
         {"0.5", "0.24"}, 
         {"0.6", "0.33"}, 
         {"0.8", "0.65"}, 
         {"0.9", "0.83"}, 
         {"1", "1"}});
    model.func("int1").set("funcname", "krw");
    model.func("int1").set("interp", "piecewisecubic");
    model.func().create("int2", "Interpolation");
    model.func("int2")
         .set("table", new String[][]{{"0.22", "1"}, 
         {"0.3", "0.4"}, 
         {"0.4", "0.125"}, 
         {"0.5", "0.0649"}, 
         {"0.6", "0.0048"}, 
         {"0.8", "0"}, 
         {"0.9", "0"}, 
         {"1", "0"}});
    model.func("int2").set("funcname", "krn");
    model.func("int2").set("interp", "piecewisecubic");
    model.func().create("int3", "Interpolation");
    model.func("int3")
         .set("table", new String[][]{{"0.22", "6.3"}, 
         {"0.3", "3.6"}, 
         {"0.4", "2.7"}, 
         {"0.5", "2.25"}, 
         {"0.6", "1.8"}, 
         {"0.8", "0.9"}, 
         {"0.9", "0.45"}, 
         {"1", "0"}});
    model.func("int3").set("funcname", "pc");
    model.func("int3").set("interp", "piecewisecubic");

    model.component("comp1").physics("phtr").prop("GravityEffects").set("IncludeGravity", true);
    model.component("comp1").physics("phtr").feature("porous1").feature("fluid1").setIndex("pc", "pc(sw)[psi]", 1);
    model.component("comp1").physics("phtr").feature("porous1").feature("fluid1").set("rhoint_sw_mat", "userdef");
    model.component("comp1").physics("phtr").feature("porous1").feature("fluid1").set("rhoint_sw", "rhow");
    model.component("comp1").physics("phtr").feature("porous1").feature("fluid1").set("mu_sw_mat", "userdef");
    model.component("comp1").physics("phtr").feature("porous1").feature("fluid1").set("mu_sw", "muw");
    model.component("comp1").physics("phtr").feature("porous1").feature("fluid1").set("kappar_sw", "krw(sw)");
    model.component("comp1").physics("phtr").feature("porous1").feature("fluid1").set("rhoint_sn_mat", "userdef");
    model.component("comp1").physics("phtr").feature("porous1").feature("fluid1").set("rhoint_sn", "rhoo");
    model.component("comp1").physics("phtr").feature("porous1").feature("fluid1").set("mu_sn_mat", "userdef");
    model.component("comp1").physics("phtr").feature("porous1").feature("fluid1").set("mu_sn", "muo");
    model.component("comp1").physics("phtr").feature("porous1").feature("fluid1").set("kappar_sn", "krn(sw)");
    model.component("comp1").physics("phtr").create("init2", "InitialValues", 3);
    model.component("comp1").physics("phtr").feature("init2").selection().set(6);
    model.component("comp1").physics("phtr").feature("init2").setIndex("s0", 0.711, 1);
    model.component("comp1").physics("phtr").create("init3", "InitialValues", 3);
    model.component("comp1").physics("phtr").feature("init3").selection().set(5);
    model.component("comp1").physics("phtr").feature("init3").setIndex("s0", 0.652, 1);
    model.component("comp1").physics("phtr").create("init4", "InitialValues", 3);
    model.component("comp1").physics("phtr").feature("init4").selection().set(4);
    model.component("comp1").physics("phtr").feature("init4").setIndex("s0", 0.527, 1);
    model.component("comp1").physics("phtr").create("init5", "InitialValues", 3);
    model.component("comp1").physics("phtr").feature("init5").selection().set(3);
    model.component("comp1").physics("phtr").feature("init5").setIndex("s0", 0.351, 1);
    model.component("comp1").physics("phtr").create("init6", "InitialValues", 3);
    model.component("comp1").physics("phtr").feature("init6").selection().set(2);
    model.component("comp1").physics("phtr").feature("init6").setIndex("s0", 0.131, 1);
    model.component("comp1").physics("dl").feature("porous1").feature("pm1").set("epsilon_mat", "userdef");
    model.component("comp1").physics("dl").feature("porous1").feature("pm1").set("epsilon", 0.2);
    model.component("comp1").physics("dl").feature("porous1").feature("pm1").set("kappa_mat", "userdef");
    model.component("comp1").physics("dl").feature("porous1").feature("pm1")
         .set("kappa", new String[]{"300[mD]", "0", "0", "0", "300[mD]", "0", "0", "0", "30[mD]"});
    model.component("comp1").physics("dl").feature("init1").set("p", "3700[psi]");

    model.component("comp1").multiphysics().create("wellmpe1", "Welle", 1);
    model.component("comp1").multiphysics("wellmpe1").selection().set(34);
    model.component("comp1").multiphysics("wellmpe1").set("wellInputType", "Pressure");
    model.component("comp1").multiphysics("wellmpe1").set("p0", "3700[psi]");
    model.component("comp1").multiphysics().create("wellmpe2", "Welle", 1);
    model.component("comp1").multiphysics("wellmpe2").selection().set(35);
    model.component("comp1").multiphysics("wellmpe2").set("injectionProduction", "Production");
    model.component("comp1").multiphysics("wellmpe2").set("M0", "massflow");
    model.component("comp1").multiphysics("wellmpe2").set("wellInputType_sn", "VolumeFraction");
    model.component("comp1").multiphysics("wellmpe2").set("s0_sn", 0.2);

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").set("zscale", 10);
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(2, 5, 8, 11, 14, 17);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().geom("geom1", 0);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().set(33);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", 6);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmin", 0.1);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hgradactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hgrad", 1.1);
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 3);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tunit", "d");
    model.study("std1").feature("time").set("tlist", "range(0,50,1500)");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("v1").set("scalemethod", "init");
    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u4f53\u79ef\u5206\u6570 (phtr)");
    model.result("pg1").feature().create("slc1", "Slice");
    model.result("pg1").feature("slc1").label("\u5207\u9762");
    model.result("pg1").feature("slc1").set("showsolutionparams", "on");
    model.result("pg1").feature("slc1").set("smooth", "internal");
    model.result("pg1").feature("slc1").set("showsolutionparams", "on");
    model.result("pg1").feature("slc1").set("data", "parent");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u4f53\u79ef\u5206\u6570 (phtr) 1");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").label("\u8868\u9762");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u901f\u5ea6 (dl)");
    model.result("pg3").set("titletype", "custom");
    model.result("pg3").feature().create("str1", "Streamline");
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("posmethod", "start");
    model.result("pg3").feature("str1").set("pointtype", "arrow");
    model.result("pg3").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg3").feature("str1").set("smooth", "internal");
    model.result("pg3").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("maxlen", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("data", "parent");
    model.result("pg3").feature("str1").selection().geom("geom1", 2);
    model.result("pg3").feature("str1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31);
    model.result("pg3").feature("str1").feature().create("col1", "Color");
    model.result("pg3").feature("str1").feature("col1").set("expr", "dl.U");
    model.result("pg3").feature("str1").feature("col1").set("colortable", "Rainbow");
    model.result("pg3").feature("str1").feature("col1").set("colortabletrans", "none");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("\u538b\u529b (dl)");
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").label("\u8868\u9762");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("expr", "p");
    model.result("pg4").feature("surf1").set("colortable", "Rainbow");
    model.result("pg4").feature("surf1").set("colortabletrans", "none");
    model.result("pg4").feature("surf1").set("smooth", "internal");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("slc1").active(false);
    model.result("pg1").run();
    model.result("pg1").create("mslc1", "Multislice");
    model.result("pg1").feature("mslc1").set("expr", "sn");
    model.result("pg1").feature("mslc1").set("xnumber", "5");
    model.result("pg1").feature("mslc1").set("ynumber", "5");
    model.result("pg1").feature("mslc1").set("znumber", "0");
    model.result("pg1").run();

    model.view().create("view2", 3);
    model.view("view2").camera().set("viewscaletype", "manual");
    model.view("view2").camera().set("zscale", 5);

    model.result("pg1").run();
    model.result("pg1").set("view", "view2");
    model.result("pg1").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").setIndex("looplevelinput", "interp", 0);
    model.result("pg5").setIndex("interp", "range(100,50,1500)", 0);
    model.result("pg5").create("ptgr1", "PointGraph");
    model.result("pg5").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg5").feature("ptgr1").set("linewidth", "preference");
    model.result("pg5").feature("ptgr1").selection().set(17);
    model.result("pg5").feature("ptgr1").set("expr", "wellmpe2.M0_sn/rhoo/STB");
    model.result("pg5").feature("ptgr1").set("unit", "1/d");
    model.result("pg5").feature("ptgr1").set("descractive", true);
    model.result("pg5").feature("ptgr1").set("descr", "\u91c7\u6cb9\u901f\u5ea6 (STB/day)");
    model.result("pg5").feature("ptgr1").set("legend", true);
    model.result("pg5").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg5").feature("ptgr1").setIndex("legends", "\u91c7\u6cb9\u901f\u5ea6 (STB/day)", 0);
    model.result("pg5").run();
    model.result("pg5").create("ptgr2", "PointGraph");
    model.result("pg5").feature("ptgr2").set("markerpos", "datapoints");
    model.result("pg5").feature("ptgr2").set("linewidth", "preference");
    model.result("pg5").feature("ptgr2").selection().set(17);
    model.result("pg5").feature("ptgr2").set("expr", "(massflow-wellmpe2.M0_sn)/rhow/(wellmpe2.M0_sn/rhoo)");
    model.result("pg5").feature("ptgr2").set("descractive", true);
    model.result("pg5").feature("ptgr2").set("descr", "\u6c34\u6cb9\u6bd4");
    model.result("pg5").feature("ptgr2").set("legend", true);
    model.result("pg5").feature("ptgr2").set("legendmethod", "manual");
    model.result("pg5").feature("ptgr2").setIndex("legends", "\u6c34\u6cb9\u6bd4", 0);
    model.result("pg5").run();
    model.result("pg5").label("\u91c7\u6cb9\u901f\u5ea6\u548c\u6c34\u6cb9\u6bd4");
    model.result("pg5").set("twoyaxes", true);
    model.result("pg5").setIndex("plotonsecyaxis", true, 1, 1);
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("ylabel", "\u91c7\u6cb9\u901f\u5ea6 (STB/day)");
    model.result("pg5").run();

    model.title("\u6cb9\u85cf\u6c34\u5e73\u4e95");

    model
         .description("\u672c\u4f8b\u6a21\u62df\u542b\u4e24\u4e2a\u6c34\u5e73\u4e95\u7684\u8584\u6cb9\u5c42\uff0c\u6cb9\u85cf\u4e2d\u5177\u6709\u4e24\u4e2a\u76f8\uff1a\u6c34\u548c\u6cb9\u3002\u901a\u8fc7\u5411\u4e95\u5e95\u6ce8\u6c34\u5b9e\u73b0\u91c7\u6cb9\u3002\u6a21\u578b\u7528\u4e8e\u8ba1\u7b97\u91c7\u6cb9\u901f\u7387\u548c\u6c34-\u6cb9\u4ea7\u91cf\u6bd4\u968f\u65f6\u95f4\u7684\u53d8\u5316\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("reservoir_horizontal_wells.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
