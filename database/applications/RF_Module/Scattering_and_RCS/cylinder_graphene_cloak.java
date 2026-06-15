/*
 * cylinder_graphene_cloak.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:40 by COMSOL 6.3.0.290. */
public class cylinder_graphene_cloak {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\RF_Module\\Scattering_and_RCS");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("emw", "ElectromagneticWaves", "geom1");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").set("solnum", "auto");
    model.study("std1").feature("freq").set("notsolnum", "auto");
    model.study("std1").feature("freq").set("outputmap", new String[]{});
    model.study("std1").feature("freq").set("ngenAUX", "1");
    model.study("std1").feature("freq").set("goalngenAUX", "1");
    model.study("std1").feature("freq").set("ngenAUX", "1");
    model.study("std1").feature("freq").set("goalngenAUX", "1");
    model.study("std1").feature("freq").setSolveFor("/physics/emw", true);

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");

    model.param().set("f0", "3[THz]");
    model.param().descr("f0", "\u8bbe\u8ba1\u7684\u9690\u5f62\u9891\u7387");
    model.param().set("lda0", "c_const/f0");
    model.param().descr("lda0", "\u8bbe\u8ba1\u7684\u9690\u5f62\u6ce2\u957f");
    model.param().set("theta", "pi/3");
    model.param().descr("theta", "\u5165\u5c04\u89d2");
    model.param().set("D", "lda0/5");
    model.param().descr("D", "\u5706\u67f1\u76f4\u5f84");
    model.param().set("L", "3*lda0");
    model.param().descr("L", "\u5706\u67f1\u957f\u5ea6");
    model.param().set("eps_d", "3.9");
    model.param().descr("eps_d", "\u5706\u67f1\u4ecb\u7535\u5e38\u6570");
    model.param().set("Ef", "0.51[eV]");
    model.param().descr("Ef", "\u77f3\u58a8\u70ef\u7684\u8d39\u7c73\u80fd\u7ea7");
    model.param().set("tau", "0.5[ps]");
    model.param().descr("tau", "\u6563\u5c04\u65f6\u95f4");
    model.param().set("T", "300[K]");
    model.param().descr("T", "\u6e29\u5ea6");
    model.param().set("d_eff", "1[nm]");
    model.param().descr("d_eff", "\u6709\u6548\u77f3\u58a8\u70ef\u539a\u5ea6");
    model.param().set("E0", "1[V/m]");
    model.param().descr("E0", "\u5165\u5c04\u573a\u7684\u7535\u573a\u5927\u5c0f");
    model.param().set("I0", "E0^2/(2*Z0_const)");
    model.param().descr("I0", "\u5165\u5c04\u529f\u7387");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"D/2", "L"});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"0", "-L/2"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"lda0", "L+lda0"});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"0", "-(L+lda0)/2"});
    model.component("comp1").geom("geom1").feature("r2").setIndex("layer", "lda0/5", 0);
    model.component("comp1").geom("geom1").feature("r2").set("layerright", true);
    model.component("comp1").geom("geom1").feature("r2").set("layertop", true);
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new String[]{"D/2+lda0/5", "L+lda0/5"});
    model.component("comp1").geom("geom1").feature("r3").set("pos", new String[]{"0", "-(L+lda0/5)/2"});
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").cpl().create("intop1", "Integration");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop1").selection().set(6, 12, 17);

    model.component("comp1").func().create("an1", "Analytic");
    model.component("comp1").func("an1").set("funcname", "H");
    model.component("comp1").func("an1")
         .set("expr", "sinh(hbar_const*x/(k_B_const*T))/(cosh(hbar_const*x/(k_B_const*T))+cosh(Ef/(k_B_const*T)))");
    model.component("comp1").func("an1").setIndex("argunit", "rad/s", 0);
    model.component("comp1").func("an1").setIndex("plotargs", "1e16", 0, 2);

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("Omega", "1[rad/s]");
    model.component("comp1").variable("var1").descr("Omega", "\u79ef\u5206\u53d8\u91cf");
    model.component("comp1").variable("var1")
         .set("integral", "integrate((H(Omega)-H(emw.omega/2))/(emw.omega^2-4*Omega^2),Omega,0[rad/s],1e16[rad/s])");
    model.component("comp1").variable("var1")
         .descr("integral", "\u5e26\u95f4\u7535\u5bfc\u7387\u65b9\u7a0b\u4e2d\u7684\u79ef\u5206");
    model.component("comp1").variable("var1")
         .set("sigma_intra", "((2*k_B_const*T*e_const^2)/(pi*hbar_const^2))*(log(2*cosh(Ef/(2*k_B_const*T)))*(-j/(emw.omega-j/tau)))");
    model.component("comp1").variable("var1").descr("sigma_intra", "\u5e26\u5185\u7535\u5bfc\u7387");
    model.component("comp1").variable("var1")
         .set("sigma_inter", "(e_const^2/(4*hbar_const))*(H(emw.omega/2)-(j*4*emw.omega/pi)*integral)");
    model.component("comp1").variable("var1").descr("sigma_inter", "\u5e26\u95f4\u7535\u5bfc\u7387");
    model.component("comp1").variable("var1").set("sigma", "sigma_intra+sigma_inter");
    model.component("comp1").variable("var1").descr("sigma", "\u603b\u77f3\u58a8\u70ef\u7535\u5bfc\u7387");
    model.component("comp1").variable("var1").set("C_scatt", "intop1(nr*emw.relPoavr+nz*emw.relPoavz)/I0");
    model.component("comp1").variable("var1").descr("C_scatt", "\u6563\u5c04\u622a\u9762");

    model.component("comp1").coordSystem().create("pml1", "PML");
    model.component("comp1").coordSystem("pml1").selection().set(1, 5, 6, 7, 8);
    model.component("comp1").coordSystem("pml1").set("ScalingType", "Cylindrical");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u7a7a\u6c14");
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermittivity", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("electricconductivity", new String[]{"0"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u7535\u4ecb\u8d28");
    model.component("comp1").material("mat2").selection().set(4);
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermittivity", new String[]{"eps_d"});
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("electricconductivity", new String[]{"0"});

    model.component("comp1").physics("emw").prop("BackgroundField").set("SolveFor", "scatteredField");
    model.component("comp1").physics("emw").prop("BackgroundField").set("WaveType", "LinearPol");
    model.component("comp1").physics("emw").prop("BackgroundField").set("incAngle", "theta");
    model.component("comp1").physics("emw").prop("BackgroundField").set("highestMode", 5);
    model.component("comp1").physics("emw").prop("BackgroundField").set("fieldAmplitude", "E0");

    model.param().set("modeNum", "0");
    model.param().descr("modeNum", "\u65b9\u4f4d\u89d2\u6a21\u6570");
    model.param().set("highestMode", "5");
    model.param().descr("highestMode", "\u6269\u5c55\u4e2d\u7684\u6700\u9ad8\u6a21\u6570");

    model.component("comp1").physics("emw").prop("outofplanewavenumber").set("mFloquet", "modeNum");

    model.study("std1").feature("freq").setIndex("plistarr_aux", "", 0);
    model.study("std1").feature("freq").setIndex("pname_aux", "modeNum", 0);
    model.study("std1").feature("freq").setIndex("plistarr_aux", "range(-highestMode,1,highestMode)", 0);

    model.component("comp1").physics("emw").create("trans1", "TransitionBoundaryCondition", 1);
    model.component("comp1").physics("emw").feature("trans1").selection().set(8, 10, 16);
    model.component("comp1").physics("emw").feature("trans1").set("epsilonr_mat", "userdef");
    model.component("comp1").physics("emw").feature("trans1").set("murbnd_mat", "userdef");
    model.component("comp1").physics("emw").feature("trans1").set("sigmabnd_mat", "userdef");
    model.component("comp1").physics("emw").feature("trans1").set("sigmabnd", "sigma/d_eff");
    model.component("comp1").physics("emw").feature("trans1").set("d", "d_eff");

    model.component("comp1").mesh("mesh1").autoMeshSize(1);

    model.study("std1").feature("freq").set("punit", "THz");
    model.study("std1").feature("freq").set("plist", "range(1,0.2,5)");
    model.study().create("std2");
    model.study("std2").create("freq", "Frequency");
    model.study("std2").feature("freq").set("solnum", "auto");
    model.study("std2").feature("freq").set("notsolnum", "auto");
    model.study("std2").feature("freq").set("outputmap", new String[]{});
    model.study("std2").feature("freq").set("ngenAUX", "1");
    model.study("std2").feature("freq").set("goalngenAUX", "1");
    model.study("std2").feature("freq").set("ngenAUX", "1");
    model.study("std2").feature("freq").set("goalngenAUX", "1");
    model.study("std2").feature("freq").setSolveFor("/physics/emw", true);
    model.study("std2").feature("freq").set("useadvanceddisable", true);
    model.study("std2").feature("freq").set("disabledphysics", new String[]{"emw/trans1"});
    model.study("std2").feature("freq").set("punit", "THz");
    model.study("std2").feature("freq").set("plist", "range(1, 0.2, 5)");
    model.study("std2").feature("freq").set("useparam", true);
    model.study("std2").feature("freq").setIndex("pname_aux", "f0", 0);
    model.study("std2").feature("freq").setIndex("plistarr_aux", "", 0);
    model.study("std2").feature("freq").setIndex("punit_aux", "Hz", 0);
    model.study("std2").feature("freq").setIndex("pname_aux", "f0", 0);
    model.study("std2").feature("freq").setIndex("plistarr_aux", "", 0);
    model.study("std2").feature("freq").setIndex("punit_aux", "Hz", 0);
    model.study("std2").feature("freq").setIndex("pname_aux", "modeNum", 0);
    model.study("std2").feature("freq").setIndex("plistarr_aux", "range(-highestMode,1,highestMode)", 0);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u7535\u573a (emw)");
    model.result("pg1").set("dataisaxisym", "off");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").label("\u4e8c\u7ef4\u65cb\u8f6c");
    model.result().dataset("rev1").set("data", "none");
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("data", "dset1");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "\u6563\u5c04\u7535\u573a\uff0cz \u5206\u91cf");
    model.result("pg2").set("paramindicator", "");
    model.result("pg2").label("\u6563\u5c04\u573a (emw)");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1")
         .set("expr", "sum(withsol('sol1',emw.relEz,setval(freq,freq),setind(modeNum,index)),index,1,2*highestMode+1)");
    model.result("pg2").feature("surf1").set("colortable", "RainbowLight");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("titletype", "manual");
    model.result("pg3").set("title", "\u80cc\u666f\u7535\u573a\uff0cz \u5206\u91cf");
    model.result("pg3").set("paramindicator", "");
    model.result("pg3").label("\u80cc\u666f\u573a (emw)");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1")
         .set("expr", "sum(withsol('sol1',emw.Ebz,setval(freq,freq),setind(modeNum,index)),index,1,2*highestMode+1)");
    model.result("pg3").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg1").run();

    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("\u7535\u573a (emw) 1");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").setIndex("looplevel", 11, 0);
    model.result("pg4").setIndex("looplevel", 21, 1);
    model.result("pg4").set("dataisaxisym", "off");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").set("showlegendsmaxmin", true);
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").label("\u8868\u9762");
    model.result("pg4").feature("surf1").set("smooth", "internal");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result().dataset().create("rev2", "Revolve2D");
    model.result().dataset("rev2").label("\u4e8c\u7ef4\u65cb\u8f6c 1");
    model.result().dataset("rev2").set("data", "none");
    model.result().dataset("rev2").set("startangle", -90);
    model.result().dataset("rev2").set("revangle", 225);
    model.result().dataset("rev2").set("data", "dset2");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").set("titletype", "manual");
    model.result("pg5").set("title", "\u6563\u5c04\u7535\u573a\uff0cz \u5206\u91cf");
    model.result("pg5").set("paramindicator", "");
    model.result("pg5").label("\u6563\u5c04\u573a (emw) 1");
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1")
         .set("expr", "sum(withsol('sol2',emw.relEz,setval(freq,freq),setind(modeNum,index)),index,1,2*highestMode+1)");
    model.result("pg5").feature("surf1").set("colortable", "RainbowLight");
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").set("titletype", "manual");
    model.result("pg6").set("title", "\u80cc\u666f\u7535\u573a\uff0cz \u5206\u91cf");
    model.result("pg6").set("paramindicator", "");
    model.result("pg6").label("\u80cc\u666f\u573a (emw) 1");
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1")
         .set("expr", "sum(withsol('sol2',emw.Ebz,setval(freq,freq),setind(modeNum,index)),index,1,2*highestMode+1)");
    model.result("pg6").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg4").run();
    model.result().dataset().create("mir1", "Mirror2D");
    model.result().dataset("mir1").set("hasvar", true);
    model.result().dataset().create("mir2", "Mirror2D");
    model.result().dataset("mir2").set("data", "dset2");
    model.result().dataset("mir2").set("hasvar", true);
    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").run();
    model.result("pg7").label("\u603b\u573a\uff08\u91c7\u7528\u77f3\u58a8\u70ef\u6597\u7bf7\uff09");
    model.result("pg7").set("data", "mir1");
    model.result("pg7").setIndex("looplevel", 11, 1);
    model.result("pg7").set("titletype", "manual");
    model.result("pg7")
         .set("title", "\u603b\u573a\uff0cz \u5206\u91cf\uff08\u91c7\u7528\u77f3\u58a8\u70ef\u6597\u7bf7\uff09");
    model.result("pg7").set("paramindicator", "");
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1")
         .set("expr", "sum(withsol('sol1',emw.Ez,setval(freq,freq),setind(modeNum,index)),index,1,2*highestMode+1)");
    model.result("pg7").feature("surf1").set("rangecoloractive", true);
    model.result("pg7").feature("surf1").set("rangecolormin", -1);
    model.result("pg7").feature("surf1").set("rangecolormax", 1);
    model.result("pg7").feature("surf1").create("filt1", "Filter");
    model.result("pg7").run();
    model.result("pg7").feature("surf1").feature("filt1").set("expr", "mir1x>0");
    model.result("pg7").run();
    model.result("pg7").create("surf2", "Surface");
    model.result("pg7").feature("surf2")
         .set("expr", "sum(withsol('sol1',emw.Ez*exp(-j*modeNum*pi),setval(freq,freq),setind(modeNum,index)),index,1,2*highestMode+1)");
    model.result("pg7").feature("surf2").set("colorlegend", false);
    model.result("pg7").feature("surf2").set("inheritplot", "surf1");
    model.result("pg7").feature("surf2").create("filt1", "Filter");
    model.result("pg7").run();
    model.result("pg7").feature("surf2").feature("filt1").set("expr", "mir1x<0");
    model.result("pg7").run();
    model.result().create("pg8", "PlotGroup2D");
    model.result("pg8").run();
    model.result("pg8").label("\u603b\u573a\uff08\u4e0d\u91c7\u7528\u77f3\u58a8\u70ef\u6597\u7bf7\uff09");
    model.result("pg8").set("data", "mir2");
    model.result("pg8").setIndex("looplevel", 11, 1);
    model.result("pg8").set("titletype", "manual");
    model.result("pg8")
         .set("title", "\u603b\u573a\uff0cz \u5206\u91cf\uff08\u4e0d\u91c7\u7528\u77f3\u58a8\u70ef\u6597\u7bf7\uff09");
    model.result("pg8").set("paramindicator", "");
    model.result("pg8").create("surf1", "Surface");
    model.result("pg8").feature("surf1")
         .set("expr", "sum(withsol('sol2',emw.Ez,setval(freq,freq),setind(modeNum,index)),index,1,2*highestMode+1)");
    model.result("pg8").feature("surf1").set("rangecoloractive", true);
    model.result("pg8").feature("surf1").set("rangecolormin", -1);
    model.result("pg8").feature("surf1").set("rangecolormax", 1);
    model.result("pg8").feature("surf1").create("filt1", "Filter");
    model.result("pg8").run();
    model.result("pg8").feature("surf1").feature("filt1").set("expr", "mir2x>0");
    model.result("pg8").run();
    model.result("pg8").create("surf2", "Surface");
    model.result("pg8").feature("surf2")
         .set("expr", "sum(withsol('sol2',emw.Ez*exp(-j*modeNum*pi),setval(freq,freq),setind(modeNum,index)),index,1,2*highestMode+1)");
    model.result("pg8").feature("surf2").set("colorlegend", false);
    model.result("pg8").feature("surf2").set("inheritplot", "surf1");
    model.result("pg8").feature("surf2").create("filt1", "Filter");
    model.result("pg8").run();
    model.result("pg8").feature("surf2").feature("filt1").set("expr", "mir2x<0");
    model.result("pg8").run();
    model.result().create("pg9", "PlotGroup1D");
    model.result("pg9").run();
    model.result("pg9").label("\u6563\u5c04\u622a\u9762");
    model.result("pg9").setIndex("looplevelinput", "first", 0);
    model.result("pg9").set("xlabelactive", true);
    model.result("pg9").set("xlabel", "\u9891\u7387 (THz)");
    model.result("pg9").set("ylabelactive", true);
    model.result("pg9").set("ylabel", "\u6563\u5c04\u622a\u9762 (m^2)");
    model.result("pg9").set("titletype", "manual");
    model.result("pg9").set("title", "\u6563\u5c04\u622a\u9762");
    model.result("pg9").create("glob1", "Global");
    model.result("pg9").feature("glob1").set("markerpos", "datapoints");
    model.result("pg9").feature("glob1").set("linewidth", "preference");
    model.result("pg9").feature("glob1")
         .setIndex("expr", "sum(withsol('sol1',C_scatt,setval(freq,freq),setind(modeNum,index)),index,1,2*highestMode+1)", 0);
    model.result("pg9").feature("glob1").setIndex("unit", 1, 0);
    model.result("pg9").feature("glob1").setIndex("descr", "\u91c7\u7528\u77f3\u58a8\u70ef\u6597\u7bf7", 0);
    model.result("pg9").feature("glob1")
         .setIndex("expr", "sum(withsol('sol2',C_scatt,setval(freq,freq),setind(modeNum,index)),index,1,2*highestMode+1)", 1);
    model.result("pg9").feature("glob1").setIndex("unit", 1, 1);
    model.result("pg9").feature("glob1").setIndex("descr", "\u4e0d\u91c7\u7528\u77f3\u58a8\u70ef\u6597\u7bf7", 1);
    model.result("pg9").feature("glob1").set("xdatasolnumtype", "level2");
    model.result("pg9").feature("glob1").set("legendmethod", "manual");
    model.result("pg9").feature("glob1").setIndex("legends", "\u91c7\u7528\u77f3\u58a8\u70ef\u6597\u7bf7", 0);
    model.result("pg9").feature("glob1").setIndex("legends", "\u4e0d\u91c7\u7528\u77f3\u58a8\u70ef\u6597\u7bf7", 1);
    model.result("pg9").run();
    model.result("pg9").set("ylog", true);
    model.result().dataset("rev1").set("revangle", 360);
    model.result().dataset("rev1").set("hasspacevars", true);
    model.result().dataset("rev2").set("revangle", 360);
    model.result().dataset("rev2").set("hasspacevars", true);
    model.result().create("pg10", "PlotGroup3D");
    model.result("pg10").run();
    model.result("pg10").label("\u4e09\u7ef4\u4e2d\u7684\u603b\u573a\u6bd4\u8f83");
    model.result("pg10").setIndex("looplevel", 11, 1);
    model.result("pg10").set("titletype", "none");
    model.result("pg10").set("edges", false);
    model.result("pg10").set("showlegends", false);
    model.result("pg10").create("slc1", "Slice");
    model.result("pg10").feature("slc1")
         .set("expr", "sum(withsol('sol1',emw.Ez*exp(-j*modeNum*rev1phi),setval(freq,freq),setind(modeNum,index)),index,1,2*highestMode+1)");
    model.result("pg10").feature("slc1").set("quickplane", "xy");
    model.result("pg10").feature("slc1").set("quickznumber", 2);
    model.result("pg10").feature("slc1").set("rangecoloractive", true);
    model.result("pg10").feature("slc1").set("rangecolormin", -1);
    model.result("pg10").feature("slc1").set("rangecolormax", 1);
    model.result("pg10").feature().duplicate("slc2", "slc1");
    model.result("pg10").run();
    model.result("pg10").feature("slc2").set("quickplane", "zx");
    model.result("pg10").feature("slc2").set("quickynumber", 1);
    model.result("pg10").feature("slc2").set("inheritplot", "slc1");
    model.result("pg10").run();
    model.result("pg10").create("surf1", "Surface");
    model.result("pg10").feature("surf1")
         .set("expr", "sum(withsol('sol1',emw.Ez*exp(-j*modeNum*rev1phi),setval(freq,freq),setind(modeNum,index)),index,1,2*highestMode+1)");
    model.result("pg10").feature("surf1").set("colortable", "Thermal");
    model.result("pg10").feature("surf1").create("filt1", "Filter");
    model.result("pg10").run();
    model.result("pg10").feature("surf1").feature("filt1")
         .set("expr", "sqrt(rev1x^2+rev1y^2)<D/2 && abs(rev1z)<L/2");
    model.result("pg10").run();
    model.result("pg10").create("slc3", "Slice");
    model.result("pg10").feature("slc3").set("data", "rev2");
    model.result("pg10").feature("slc3").setIndex("looplevel", 11, 1);
    model.result("pg10").feature("slc3")
         .set("expr", "sum(withsol('sol2',emw.Ez*exp(-j*modeNum*rev2phi),setval(freq,freq),setind(modeNum,index)),index,1,2*highestMode+1)");
    model.result("pg10").feature("slc3").set("quickplane", "xy");
    model.result("pg10").feature("slc3").set("quickznumber", 2);
    model.result("pg10").feature("slc3").set("inheritplot", "slc1");
    model.result("pg10").feature("slc3").create("trn1", "Transformation");
    model.result("pg10").run();
    model.result("pg10").feature("slc3").feature("trn1").set("move", new int[]{200, 100, 0});
    model.result("pg10").run();
    model.result("pg10").create("slc4", "Slice");
    model.result("pg10").feature("slc4").set("data", "rev2");
    model.result("pg10").feature("slc4").setIndex("looplevel", 11, 1);
    model.result("pg10").feature("slc4")
         .set("expr", "sum(withsol('sol2',emw.Ez*exp(-j*modeNum*rev2phi),setval(freq,freq),setind(modeNum,index)),index,1,2*highestMode+1)");
    model.result("pg10").feature("slc4").set("quickplane", "zx");
    model.result("pg10").feature("slc4").set("quickynumber", 1);
    model.result("pg10").feature("slc4").set("inheritplot", "slc1");
    model.result("pg10").feature("slc4").create("trn1", "Transformation");
    model.result("pg10").run();
    model.result("pg10").feature("slc4").feature("trn1").set("move", new int[]{200, 100, 0});
    model.result("pg10").run();
    model.result("pg10").create("surf2", "Surface");
    model.result("pg10").feature("surf2").set("data", "rev2");
    model.result("pg10").feature("surf2").setIndex("looplevel", 11, 1);
    model.result("pg10").feature("surf2")
         .set("expr", "sum(withsol('sol2',emw.Ez*exp(-j*modeNum*rev2phi),setval(freq,freq),setind(modeNum,index)),index,1,2*highestMode+1)");
    model.result("pg10").feature("surf2").set("inheritplot", "surf1");
    model.result("pg10").feature("surf2").create("trn1", "Transformation");
    model.result("pg10").run();
    model.result("pg10").feature("surf2").feature("trn1").set("move", new int[]{200, 100, 0});
    model.result("pg10").run();
    model.result("pg10").feature("surf2").create("filt1", "Filter");
    model.result("pg10").run();
    model.result("pg10").feature("surf2").feature("filt1")
         .set("expr", "sqrt(rev2x^2+rev2y^2)<D/2 && abs(rev2z)<L/2");

    model.view("view4").camera().set("zoomanglefull", 22.414417266845703);
    model.view("view4").camera().setIndex("position", 1027.87939453125, 0);
    model.view("view4").camera().setIndex("position", -2057.474365234375, 1);
    model.view("view4").camera().setIndex("position", 903.16552734375, 2);
    model.view("view4").camera().set("target", new double[]{-7.38037109375, 0, 0});
    model.view("view4").camera().setIndex("target", 41.393798828125, 1);
    model.view("view4").camera().setIndex("target", -6.969482421875, 2);
    model.view("view4").camera().setIndex("up", -0.14546343684196472, 0);
    model.view("view4").camera().setIndex("up", 0.33233827352523804, 1);
    model.view("view4").camera().setIndex("up", 0.931868851184845, 2);
    model.view("view4").camera().set("rotationpoint", new int[]{100, 50, 5});
    model.view("view4").camera().setIndex("viewoffset", -0.11194504052400589, 0);
    model.view("view4").camera().setIndex("viewoffset", 0.05399199202656746, 1);

    model.result("pg10").run();
    model.result("pg10").create("ann1", "Annotation");
    model.result("pg10").feature("ann1").set("text", "\u91c7\u7528\u77f3\u58a8\u70ef\u6597\u7bf7");
    model.result("pg10").feature("ann1").set("posxexpr", -100);
    model.result("pg10").feature("ann1").set("poszexpr", 230);
    model.result("pg10").feature("ann1").set("showpoint", false);
    model.result("pg10").feature().duplicate("ann2", "ann1");
    model.result("pg10").run();
    model.result("pg10").feature("ann2").set("text", "\u4e0d\u91c7\u7528\u77f3\u58a8\u70ef\u6597\u7bf7");
    model.result("pg10").feature("ann2").set("posxexpr", 150);
    model.result("pg10").feature("ann2").set("poszexpr", 260);
    model.result("pg10").feature().duplicate("ann3", "ann2");
    model.result("pg10").run();
    model.result("pg10").feature("ann3")
         .set("text", "C_scatt = eval(sum(withsol('sol1',C_scatt,setval(freq,freq),setind(modeNum,index)),index,1,2*highestMode+1), um^2) um^2");
    model.result("pg10").feature("ann3").set("posxexpr", 0);
    model.result("pg10").feature("ann3").set("poszexpr", 0);
    model.result("pg10").feature().duplicate("ann4", "ann3");
    model.result("pg10").run();
    model.result("pg10").run();
    model.result("pg10").feature("ann3").create("trn1", "Transformation");
    model.result("pg10").run();
    model.result("pg10").feature("ann3").feature("trn1").set("move", new int[]{-130, -20, -220});
    model.result("pg10").run();
    model.result("pg10").feature("ann4")
         .set("text", "C_scatt = eval(sum(withsol('sol2',C_scatt,setval(freq,3[THz]),setind(modeNum,index)),index,1,2*highestMode+1), um^2) um^2");
    model.result("pg10").feature("ann4").create("trn1", "Transformation");
    model.result("pg10").run();
    model.result("pg10").feature("ann4").feature("trn1").set("move", new int[]{140, 40, -200});

    model.view("view4").set("showgrid", false);
    model.view("view4").set("showaxisorientation", false);

    model.title("\u77f3\u58a8\u70ef\u5bf9\u5706\u67f1\u6563\u5c04\u4f53\u7684\u9690\u8eab\u4f5c\u7528");

    model
         .description("\u672c\u6a21\u578b\u4ecb\u7ecd\u4e00\u79cd\u4f7f\u7528\u7535\u8c03\u8c10\u5355\u5c42\u77f3\u58a8\u70ef\u7684\u9690\u8eab\u65b9\u6cd5\uff0c\u6f14\u793a\u4e86\u5f53\u4e00\u4e2a\u5706\u67f1\u5f62\u7535\u4ecb\u8d28\u6563\u5c04\u4f53\u88ab\u77f3\u58a8\u70ef\u8986\u76d6\u65f6\uff0c\u6563\u5c04\u622a\u9762\u5728\u6307\u5b9a\u9891\u7387\u4e0b\u4f1a\u5927\u5e45\u51cf\u5c0f\uff0c\u4ece\u800c\u4f7f\u5176\u5728\u7535\u78c1\u4e0a\u4e0d\u53ef\u89c1\u3002\u8be5\u6a21\u578b\u65e8\u5728\u8bf4\u660e\u7ebf\u504f\u632f\u5e73\u9762\u6ce2\u80cc\u666f\u573a\u5728\u4e8c\u7ef4\u8f74\u5bf9\u79f0\u6a21\u578b\u4e2d\u7684\u5e94\u7528\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("cylinder_graphene_cloak.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
