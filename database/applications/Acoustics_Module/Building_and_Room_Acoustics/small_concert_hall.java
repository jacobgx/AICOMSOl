/*
 * small_concert_hall.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:26 by COMSOL 6.3.0.290. */
public class small_concert_hall {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Building_and_Room_Acoustics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("rac", "RayAcoustics", "geom1");

    model.study().create("std1");
    model.study("std1").create("rtrac", "RayTracing");
    model.study("std1").feature("rtrac").setSolveFor("/physics/rac", true);

    model.component("comp1").geom("geom1").insertFile("small_concert_hall_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.param().label("\u53c2\u6570 1 - \u6a21\u578b");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("f0", "500[Hz]", "\u9891\u5e26\u4e2d\u5fc3\u9891\u7387");
    model.param().set("c0", "343[m/s]", "\u58f0\u901f");
    model.param().set("rho0", "1.2[kg/m^3]", "\u5bc6\u5ea6");
    model.param().set("lam0", "c0/f0", "f0 \u7684\u6ce2\u957f");
    model.param().set("Vol", "412.5[m^3]", "\u623f\u95f4\u4f53\u79ef");
    model.param().set("s_default", "0.05", "\u5e73\u5766\u8868\u9762\u7684\u6563\u5c04\u7cfb\u6570");
    model.param().set("s_diffuser", "0.5", "\u6269\u97f3\u5668\u7684\u6563\u5c04\u7cfb\u6570");
    model.param().set("s_seats", "0.6", "\u5ea7\u6905\u533a\u7684\u6563\u5c04\u7cfb\u6570");
    model.param().create("par2");
    model.param("par2").label("\u53c2\u6570 2 - \u6e90\u548c\u63a5\u6536\u5668\u4f4d\u7f6e");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("x_src", "1[m]", "\u5168\u5411\u6e90 x \u5750\u6807");
    model.param("par2").set("y_src", "-1[m]", "\u5168\u5411\u6e90 y \u5750\u6807");
    model.param("par2").set("z_src", "1.5[m]", "\u5168\u5411\u6e90 z \u5750\u6807");
    model.param("par2").set("x_rec", "7[m]", "\u63a5\u6536\u5668 x \u5750\u6807");
    model.param("par2").set("y_rec", "1[m]", "\u63a5\u6536\u5668 y \u5750\u6807");
    model.param("par2").set("z_rec", "1.5[m]", "\u63a5\u6536\u5668 z \u5750\u6807");
    model.param("par2")
         .set("dsr", "sqrt((x_src-x_rec)^2+(y_src-y_rec)^2+(z_src-z_rec)^2)", "\u6e90\u4e0e\u63a5\u6536\u5668\u7684\u8ddd\u79bb");
    model.param("par2").set("x_spk", "0.8[m]", "\u626c\u58f0\u5668 x \u5750\u6807");
    model.param("par2").set("y_spk", "2[m]", "\u626c\u58f0\u5668 y \u5750\u6807");
    model.param("par2").set("z_spk", "2.5[m]", "\u626c\u58f0\u5668 z \u5750\u6807");
    model.param("par2").set("alpha0", "90[deg]", "\u626c\u58f0\u5668\u65b9\u5411\uff08\u6b27\u62c9\u89d2\uff09");
    model.param("par2").set("beta0", "15[deg]", "\u626c\u58f0\u5668\u65b9\u5411\uff08\u6b27\u62c9\u89d2\uff09");
    model.param("par2").set("gamma0", "-100[deg]", "\u626c\u58f0\u5668\u65b9\u5411\uff08\u6b27\u62c9\u89d2\uff09");
    model.param().create("par3");
    model.param("par3").label("\u53c2\u6570 3 - \u6e90\u548c\u63a5\u6536\u5668\u8bbe\u7f6e");

//    To import content from file, use:
//    model.param("par3").loadFile("FILENAME");
    model.param("par3").set("L0_src", "100[dB]", "R0_src \u5904\u7684\u5168\u5411\u6e90\u58f0\u538b\u7ea7");
    model.param("par3").set("R0_src", "1[m]", "\u53c2\u8003\u8ddd\u79bb");
    model.param("par3").set("L0_spk", "0[dB]", "\u626c\u58f0\u5668\u589e\u76ca");
    model.param("par3").set("R0_spk", "1[m]", "\u53c2\u8003\u8ddd\u79bb");
    model.param("par3").set("r_rec", "0.3[m]", "\u63a5\u6536\u5668\u534a\u5f84");
    model.param("par3").set("dt", "0.01[s]", "\u65f6\u95f4\u95f4\u9694");
    model.param("par3")
         .set("Nrays_min", "(4.34/r_rec)^2*Vol/pi/c0/dt", "\u5efa\u8bae\u7684\u6700\u5c0f\u91ca\u653e\u5c04\u7ebf\u6570");
    model.param("par3").set("Nrays", "1000*ceil(Nrays_min/1000)", "\u91ca\u653e\u7684\u5c04\u7ebf\u6570");

    model.func().create("int1", "Interpolation");
    model.func("int1").set("source", "file");
    model.func("int1").set("filename", "small_concert_hall_radiation_balloon.txt");
    model.func("int1").importData();
    model.func("int1").setIndex("argunit", "deg", 0);
    model.func("int1").setIndex("argunit", "deg", 1);
    model.func("int1").setIndex("argunit", "Hz", 2);
    model.func("int1").setEntry("funcnames", "col4", "preal");
    model.func("int1").setIndex("fununit", "Pa", 0);
    model.func("int1").setEntry("columnType", "col5", "value");
    model.func("int1").setEntry("funcnames", "col5", "pimag");
    model.func("int1").setIndex("fununit", "Pa", 1);
    model.func().create("int2", "Interpolation");
    model.func("int2").set("source", "file");
    model.func("int2").set("filename", "small_concert_hall_absorption_parameters.txt");
    model.func("int2").importData();
    model.func("int2").setEntry("columnType", "col2", "value");
    model.func("int2").setEntry("columnType", "col3", "value");
    model.func("int2").setEntry("columnType", "col5", "value");
    model.func("int2").setEntry("columnType", "col6", "value");
    model.func("int2").setEntry("columnType", "col7", "value");
    model.func("int2").setEntry("columnType", "col8", "value");
    model.func("int2").setIndex("argunit", "Hz", 0);
    model.func("int2").setEntry("funcnames", "col2", "a_walls");
    model.func("int2").setIndex("fununit", "1", 0);
    model.func("int2").setEntry("funcnames", "col3", "a_entrance");
    model.func("int2").setIndex("fununit", "1", 1);
    model.func("int2").setEntry("funcnames", "col4", "a_windows");
    model.func("int2").setIndex("fununit", "1", 2);
    model.func("int2").setEntry("funcnames", "col5", "a_floor");
    model.func("int2").setIndex("fununit", "1", 3);
    model.func("int2").setEntry("funcnames", "col6", "a_diffuser");
    model.func("int2").setIndex("fununit", "1", 4);
    model.func("int2").setEntry("funcnames", "col7", "a_seats");
    model.func("int2").setIndex("fununit", "1", 5);
    model.func("int2").setEntry("funcnames", "col8", "a_absorbers");
    model.func("int2").setIndex("fununit", "1", 6);
    model.func().create("int3", "Interpolation");
    model.func("int3").set("source", "file");
    model.func("int3").set("filename", "small_concert_hall_air_attenuation.txt");
    model.func("int3").importData();
    model.func("int3").set("funcname", "a_air");
    model.func("int3").set("interp", "neighbor");
    model.func("int3").setIndex("argunit", "Hz", 0);
    model.func("int3").setIndex("fununit", "1/m", 0);

    model.component("comp1").coordSystem().create("sys2", "Rotated");
    model.component("comp1").coordSystem("sys2").set("angle", new String[]{"alpha0", "beta0", "0"});
    model.component("comp1").coordSystem("sys2").setIndex("angle", "gamma0", 2);

    model.component("comp1").geom("geom1").run("sel7");
    model.component("comp1").geom("geom1").create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", "x_spk", 0);
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", "y_spk", 1);
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", "z_spk", 2);
    model.component("comp1").geom("geom1").run("pt1");
    model.component("comp1").geom("geom1").create("sph1", "Sphere");
    model.component("comp1").geom("geom1").feature("sph1").set("type", "surface");
    model.component("comp1").geom("geom1").feature("sph1").set("r", "r_rec");
    model.component("comp1").geom("geom1").feature("sph1").set("pos", new String[]{"x_rec", "y_rec", "z_rec"});
    model.component("comp1").geom("geom1").feature("sph1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("sph1").set("selresultshow", "bnd");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").set("opname", "intop_windows");
    model.component("comp1").cpl("intop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop1").selection().named("geom1_sel1");
    model.component("comp1").cpl().create("intop2", "Integration");
    model.component("comp1").cpl("intop2").set("axisym", true);
    model.component("comp1").cpl("intop2").set("opname", "intop_seats");
    model.component("comp1").cpl("intop2").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop2").selection().named("geom1_sel2");
    model.component("comp1").cpl().create("intop3", "Integration");
    model.component("comp1").cpl("intop3").set("axisym", true);
    model.component("comp1").cpl("intop3").set("opname", "intop_diffusers");
    model.component("comp1").cpl("intop3").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop3").selection().named("geom1_sel3");
    model.component("comp1").cpl().create("intop4", "Integration");
    model.component("comp1").cpl("intop4").set("axisym", true);
    model.component("comp1").cpl("intop4").set("opname", "intop_floor");
    model.component("comp1").cpl("intop4").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop4").selection().named("geom1_sel4");
    model.component("comp1").cpl().create("intop5", "Integration");
    model.component("comp1").cpl("intop5").set("axisym", true);
    model.component("comp1").cpl("intop5").set("opname", "intop_entrance");
    model.component("comp1").cpl("intop5").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop5").selection().named("geom1_sel5");
    model.component("comp1").cpl().create("intop6", "Integration");
    model.component("comp1").cpl("intop6").set("axisym", true);
    model.component("comp1").cpl("intop6").set("opname", "intop_walls");
    model.component("comp1").cpl("intop6").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop6").selection().named("geom1_sel6");
    model.component("comp1").cpl().create("intop7", "Integration");
    model.component("comp1").cpl("intop7").set("axisym", true);
    model.component("comp1").cpl("intop7").set("opname", "intop_absorbers");
    model.component("comp1").cpl("intop7").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop7").selection().named("geom1_sel7");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").label("\u53d8\u91cf\uff1a\u8d28\u91cf\u6307\u6807\u4f30\u8ba1");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("A", "intop_windows(1)*a_windows(f0)+intop_seats(1)*a_seats(f0)+intop_diffusers(1)*a_diffuser(f0)+intop_floor(1)*a_floor(f0)+intop_entrance(1)*a_entrance(f0)+intop_walls(1)*a_walls(f0)+intop_absorbers(1)*a_absorbers(f0)", "\u603b\u5438\u58f0\u91cf");
    model.component("comp1").variable("var1")
         .set("S", "intop_windows(1)+intop_seats(1)+intop_diffusers(1)+intop_floor(1)+intop_entrance(1)+intop_walls(1)+intop_absorbers(1)", "\u603b\u8868\u9762\u79ef");
    model.component("comp1").variable("var1")
         .set("T60_S", "0.161[s/m]*Vol/(A+8*a_air(f0)*Vol)", "\u6df7\u54cd\u65f6\u95f4 (Sabine)");
    model.component("comp1").variable("var1")
         .set("T60_Sna", "0.161[s/m]*Vol/A", "\u6df7\u54cd\u65f6\u95f4\uff08Sabine\uff0c\u65e0\u7a7a\u6c14\u5438\u6536\uff09");
    model.component("comp1").variable("var1")
         .set("T60_E", "0.161[s/m]*Vol/(-S*log(1-A/S)+8*a_air(f0)*Vol)", "\u6df7\u54cd\u65f6\u95f4 (Eyring)");
    model.component("comp1").variable("var1").set("T0", "T60_S", "\u6df7\u54cd\u65f6\u95f4");
    model.component("comp1").variable("var1").set("Ed", "1/(4*pi*c0*dsr^2)", "\u76f4\u63a5\u80fd\u91cf");
    model.component("comp1").variable("var1")
         .set("Ee50", "T0/(13.8*Vol)*(exp(-A/S)-exp(-(13.8/T0*50[ms]+A/S)))", "\u65e9\u671f\u80fd\u91cf 50 ms");
    model.component("comp1").variable("var1")
         .set("El50", "T0/(13.8*Vol)*(exp(-(13.8/T0*50[ms]+A/S)))", "\u665a\u671f\u80fd\u91cf 50 ms");
    model.component("comp1").variable("var1")
         .set("Ee80", "T0/(13.8*Vol)*(exp(-A/S)-exp(-(13.8/T0*80[ms]+A/S)))", "\u65e9\u671f\u80fd\u91cf 80 ms");
    model.component("comp1").variable("var1")
         .set("El80", "T0/(13.8*Vol)*(exp(-(13.8/T0*80[ms]+A/S)))", "\u665a\u671f\u80fd\u91cf 80 ms");
    model.component("comp1").variable("var1")
         .set("C50", "10*log10((Ed+Ee50)/El50)", "\u6e05\u6670\u5ea6 50 \u4f30\u8ba1");
    model.component("comp1").variable("var1")
         .set("C80", "10*log10((Ed+Ee80)/El80)", "\u6e05\u6670\u5ea6 80 \u4f30\u8ba1");
    model.component("comp1").variable("var1")
         .set("D", "10^(C50/10)/(1+10^(C50/10))*100", "\u5b9a\u4e49\u4f30\u8ba1");
    model.component("comp1").variable("var1").set("ts", "T0/13.8*(A/S+1)", "\u4e2d\u5fc3\u65f6\u95f4\u4f30\u8ba1");

    model.component("comp1").physics("rac").prop("IntensityComputation")
         .setIndex("IntensityComputation", "ComputeIntensityAndPower", 0);
    model.component("comp1").physics("rac").prop("cext").setIndex("cext", "c0", 0);
    model.component("comp1").physics("rac").prop("rhoext").setIndex("rhoext", "rho0", 0);
    model.component("comp1").physics("rac").prop("alphaext").setIndex("alphaext", "a_air(f0)", 0);
    model.component("comp1").physics("rac").feature("op1").set("f", "f0");
    model.component("comp1").physics("rac").create("wall2", "Wall", 2);
    model.component("comp1").physics("rac").feature("wall2").label("\u58c1\uff1a\u5899\u58c1");
    model.component("comp1").physics("rac").feature("wall2").selection().named("geom1_sel6");
    model.component("comp1").physics("rac").feature("wall2").set("WallCondition", "MixedDiffuseSpecular");
    model.component("comp1").physics("rac").feature("wall2").set("gammas", "1-s_default");
    model.component("comp1").physics("rac").feature("wall2").set("alphas", "a_walls(f0)");
    model.component("comp1").physics("rac").feature("wall2").set("alphad", "a_walls(f0)");
    model.component("comp1").physics("rac").create("wall3", "Wall", 2);
    model.component("comp1").physics("rac").feature("wall3").label("\u58c1\uff1a\u5165\u53e3");
    model.component("comp1").physics("rac").feature("wall3").selection().named("geom1_sel5");
    model.component("comp1").physics("rac").feature("wall3").set("WallCondition", "MixedDiffuseSpecular");
    model.component("comp1").physics("rac").feature("wall3").set("gammas", "1-s_default");
    model.component("comp1").physics("rac").feature("wall3").set("alphas", "a_entrance(f0)");
    model.component("comp1").physics("rac").feature("wall3").set("alphad", "a_entrance(f0)");
    model.component("comp1").physics("rac").create("wall4", "Wall", 2);
    model.component("comp1").physics("rac").feature("wall4").label("\u58c1\uff1a\u7a97\u6237");
    model.component("comp1").physics("rac").feature("wall4").selection().named("geom1_sel1");
    model.component("comp1").physics("rac").feature("wall4").set("WallCondition", "MixedDiffuseSpecular");
    model.component("comp1").physics("rac").feature("wall4").set("gammas", "1-s_default");
    model.component("comp1").physics("rac").feature("wall4").set("alphas", "a_windows(f0)");
    model.component("comp1").physics("rac").feature("wall4").set("alphad", "a_windows(f0)");
    model.component("comp1").physics("rac").create("wall5", "Wall", 2);
    model.component("comp1").physics("rac").feature("wall5").label("\u58c1\uff1a\u5730\u677f");
    model.component("comp1").physics("rac").feature("wall5").selection().named("geom1_sel4");
    model.component("comp1").physics("rac").feature("wall5").set("WallCondition", "MixedDiffuseSpecular");
    model.component("comp1").physics("rac").feature("wall5").set("gammas", "1-s_default");
    model.component("comp1").physics("rac").feature("wall5").set("alphas", "a_floor(f0)");
    model.component("comp1").physics("rac").feature("wall5").set("alphad", "a_floor(f0)");
    model.component("comp1").physics("rac").create("wall6", "Wall", 2);
    model.component("comp1").physics("rac").feature("wall6").label("\u58c1\uff1a\u6269\u97f3\u5668");
    model.component("comp1").physics("rac").feature("wall6").selection().named("geom1_sel3");
    model.component("comp1").physics("rac").feature("wall6").set("WallCondition", "MixedDiffuseSpecular");
    model.component("comp1").physics("rac").feature("wall6").set("gammas", "1-s_diffuser");
    model.component("comp1").physics("rac").feature("wall6").set("alphas", "a_diffuser(f0)");
    model.component("comp1").physics("rac").feature("wall6").set("alphad", "a_diffuser(f0)");
    model.component("comp1").physics("rac").create("wall7", "Wall", 2);
    model.component("comp1").physics("rac").feature("wall7").label("\u58c1\uff1a\u5438\u58f0\u5668");
    model.component("comp1").physics("rac").feature("wall7").selection().named("geom1_sel7");
    model.component("comp1").physics("rac").feature("wall7").set("WallCondition", "MixedDiffuseSpecular");
    model.component("comp1").physics("rac").feature("wall7").set("gammas", "1-s_default");
    model.component("comp1").physics("rac").feature("wall7").set("alphas", "a_absorbers(f0)");
    model.component("comp1").physics("rac").feature("wall7").set("alphad", "a_absorbers(f0)");
    model.component("comp1").physics("rac").create("wall8", "Wall", 2);
    model.component("comp1").physics("rac").feature("wall8")
         .label("\u58c1\uff1a\u5ea7\u6905\uff08\u9876\u90e8\uff09");
    model.component("comp1").physics("rac").feature("wall8").selection().set(41);
    model.component("comp1").physics("rac").feature("wall8").set("WallCondition", "MixedDiffuseSpecular");
    model.component("comp1").physics("rac").feature("wall8").set("gammas", "1-s_seats");
    model.component("comp1").physics("rac").feature("wall8").set("alphas", "a_seats(f0)");
    model.component("comp1").physics("rac").feature("wall8").set("alphad", "a_seats(f0)");
    model.component("comp1").physics("rac").feature("wall8").create("spl1", "SoundPressureLevelBoundary", 2);
    model.component("comp1").physics("rac").feature("wall8").feature("spl1")
         .set("ComputeSmoothedAccumulatedVariable", true);
    model.component("comp1").physics("rac").create("wall9", "Wall", 2);
    model.component("comp1").physics("rac").feature("wall9")
         .label("\u58c1\uff1a\u5ea7\u6905\uff08\u5468\u56f4\uff09");
    model.component("comp1").physics("rac").feature("wall9").selection().set(39, 40, 42, 67);
    model.component("comp1").physics("rac").feature("wall9").set("WallCondition", "MixedDiffuseSpecular");
    model.component("comp1").physics("rac").feature("wall9").set("gammas", "1-s_seats");
    model.component("comp1").physics("rac").feature("wall9").set("alphas", "a_seats(f0)");
    model.component("comp1").physics("rac").feature("wall9").set("alphad", "a_seats(f0)");
    model.component("comp1").physics("rac").create("swd1", "SourceWithDirectivity", -1);
    model.component("comp1").physics("rac").feature("swd1").set("q0", new String[]{"x_src", "y_src", "z_src"});
    model.component("comp1").physics("rac").feature("swd1").setIndex("Nw", "Nrays", 0);
    model.component("comp1").physics("rac").feature("swd1").set("D", "0[dB]");
    model.component("comp1").physics("rac").feature("swd1").set("Lref", "L0_src");
    model.component("comp1").physics("rac").feature("swd1").set("Rref", "R0_src");
    model.component("comp1").physics("rac").create("swd2", "SourceWithDirectivity", -1);
    model.component("comp1").physics("rac").feature("swd2").set("coordinateSystem", "sys2");
    model.component("comp1").physics("rac").feature("swd2").set("q0", new String[]{"x_spk", "y_spk", "z_spk"});
    model.component("comp1").physics("rac").feature("swd2").setIndex("Nw", "Nrays", 0);
    model.component("comp1").physics("rac").feature("swd2")
         .set("D", "20*log10(abs(preal(rac.swd2.phi,rac.swd2.theta,f0)+i*pimag(rac.swd2.phi,rac.swd2.theta,f0))/sqrt(2)/20e-6[Pa])");
    model.component("comp1").physics("rac").feature("swd2").set("Lref", "L0_spk");
    model.component("comp1").physics("rac").feature("swd2").set("Rref", "R0_spk");
    model.component("comp1").physics("rac").create("rt1", "RayTermination", -1);
    model.component("comp1").physics("rac").feature("rt1")
         .set("SpatialExtentsOfRayPropagation", "BoundingBoxFromGeometry");
    model.component("comp1").physics("rac").feature("rt1").set("AdditionalTerminationCriteria", "Power");
    model.component("comp1").physics("rac").feature("rt1")
         .set("Qth", "if(isdefined(rac.swd1.Q0),rac.swd1.Q0*1e-7,rac.swd2.Q0*1e-7)");
    model.component("comp1").physics("rac").create("rec1", "Receiver", 2);
    model.component("comp1").physics("rac").feature("rec1").selection().named("geom1_sph1_bnd");

    model.component("comp1").mesh("mesh1").autoMeshSize(8);
    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size2").set("hmax", "r_rec/3");
    model.component("comp1").mesh("mesh1").feature("size2").set("hminactive", false);
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().all();
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().set(41);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", 0.3);
    model.component("comp1").mesh("mesh1").create("vtx1", "Vertex");
    model.component("comp1").mesh("mesh1").feature("vtx1").selection().set(11);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7814\u7a76 1 - \u5168\u5411\u6e90");
    model.study("std1").feature("rtrac").set("tunit", "s");
    model.study("std1").feature("rtrac").set("tlist", "0 1.6");
    model.study("std1").feature("rtrac").set("useadvanceddisable", true);
    model.study("std1").feature("rtrac").set("disabledphysics", new String[]{"rac/swd2"});
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "alpha0", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "rad", 0);
    model.study("std1").feature("param").setIndex("pname", "alpha0", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "rad", 0);
    model.study("std1").feature("param").setIndex("pname", "f0", 0);
    model.study("std1").feature("param").setIndex("plistarr", "{125, 250, 500, 1e3, 2e3, 4e3, 8e3}", 0);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().dataset().create("ray1", "Ray");
    model.result().dataset("ray1").set("solution", "sol2");
    model.result().dataset("ray1").set("posdof", new String[]{"comp1.qx", "comp1.qy", "comp1.qz"});
    model.result().dataset("ray1").set("geom", "geom1");
    model.result().dataset("ray1").set("rgeom", "pgeom_rac");
    model.result().dataset("ray1").set("rgeomspec", "fromphysics");
    model.result().dataset("ray1").set("physicsinterface", "rac");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "ray1");
    model.result("pg1").setIndex("looplevel", 2, 0);
    model.result("pg1").setIndex("looplevel", 7, 1);
    model.result("pg1").label("\u5c04\u7ebf\u8f68\u8ff9 (rac)");
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").create("rtrj1", "RayTrajectories");
    model.result("pg1").feature("rtrj1").set("linetype", "line");
    model.result("pg1").feature("rtrj1").create("col1", "Color");
    model.result("pg1").feature("rtrj1").feature("col1").set("expr", "rac.I");
    model.result("pg1").feature("rtrj1").create("filt1", "RayTrajectoriesFilter");
    model.result().dataset().create("re1", "Receiver3D");
    model.result().dataset("re1").set("data", "ray1");
    model.result().dataset("re1").set("receiverselection", "comp1.rac.rec1");
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", "interp", 0);
    model.result("pg1").set("interp", new String[]{"10[ms]"});
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("rtrj1").set("linetype", "none");
    model.result("pg1").feature("rtrj1").set("pointtype", "point");
    model.result("pg1").run();
    model.result("pg1").feature("rtrj1").feature("col1").set("expr", "rac.Lp");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").set("interp", new String[]{"20[ms]"});
    model.result("pg1").run();
    model.result().export().create("anim1", "Animation");
    model.result().export("anim1").set("target", "player");
    model.result().export("anim1").set("fontsize", "9");
    model.result().export("anim1").set("colortheme", "globaltheme");
    model.result().export("anim1").set("customcolor", new double[]{1, 1, 1});
    model.result().export("anim1").set("background", "color");
    model.result().export("anim1").set("gltfincludelines", "on");
    model.result().export("anim1").set("title1d", "on");
    model.result().export("anim1").set("legend1d", "on");
    model.result().export("anim1").set("logo1d", "on");
    model.result().export("anim1").set("options1d", "on");
    model.result().export("anim1").set("title2d", "on");
    model.result().export("anim1").set("legend2d", "on");
    model.result().export("anim1").set("logo2d", "on");
    model.result().export("anim1").set("options2d", "off");
    model.result().export("anim1").set("title3d", "on");
    model.result().export("anim1").set("legend3d", "on");
    model.result().export("anim1").set("logo3d", "on");
    model.result().export("anim1").set("options3d", "off");
    model.result().export("anim1").set("axisorientation", "on");
    model.result().export("anim1").set("grid", "on");
    model.result().export("anim1").set("axes1d", "on");
    model.result().export("anim1").set("axes2d", "on");
    model.result().export("anim1").set("showgrid", "on");
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("looplevelinput", "interp");
    model.result().export("anim1").set("interp", "range(3[ms],1[ms],50[ms])");
    model.result().export("anim1").set("maxframes", 48);
    model.result().export("anim1").run();
    model.result().setOnlyPlotWhenRequested(true);
    model.result().dataset("re1").label("\u4e09\u7ef4\u63a5\u6536\u5668 - \u5168\u9891\u5e26");
    model.result().dataset().duplicate("re2", "re1");
    model.result().dataset("re2").label("\u4e09\u7ef4\u63a5\u6536\u5668 - 125 Hz \u9891\u5e26");
    model.result().dataset("re2").setIndex("looplevelinput", "manual", 1);
    model.result().dataset("re2").setIndex("looplevel", new int[]{1}, 1);
    model.result().dataset().duplicate("re3", "re2");
    model.result().dataset("re3").label("\u4e09\u7ef4\u63a5\u6536\u5668 - 8 kHz \u9891\u5e26");
    model.result().dataset("re3").setIndex("looplevel", new int[]{7}, 1);
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").label("\u8109\u51b2\u54cd\u5e94");
    model.result("pg2").set("data", "re1");
    model.result("pg2").create("imp1", "ImpulseResponse");
    model.result("pg2").feature("imp1").set("markerpos", "datapoints");
    model.result("pg2").feature("imp1").set("linewidth", "preference");
    model.result("pg2").feature("imp1").set("zeropadding", 22050);
    model.result("pg2").feature("imp1").set("ripplefactor", 0.001);
    model.result("pg2").run();
    model.result().export().create("plot1", "pg2", "imp1", "Plot");
    model.result().export("plot1").set("exporttype", "wav");
    model.result().export("plot1").set("filename", "small_concert_hall_impulse_response.wav");
    model.result().export("plot1").set("quantlevel", "16bit");
    model.result("pg2").feature("imp1").create("enyd1", "EnergyDecay");
    model.result("pg2").feature("imp1").feature("enyd1").set("bandtype", "indiband");
    model.result("pg2").feature("imp1").feature("enyd1").set("bandfreq", "All");
    model.result("pg2").feature("imp1").feature("enyd1").set("plot", "leveldecay");
    model.result("pg2").feature("imp1").feature("enyd1").set("c50", false);
    model.result("pg2").feature("imp1").feature("enyd1").set("tr", false);
    model.result("pg2").feature("imp1").feature("enyd1").set("snr", false);
    model.result("pg2").feature("imp1").set("legend", true);
    model.result("pg2").run();

    model.func().create("int4", "Interpolation");
    model.func("int4").set("source", "file");
    model.func("int4").set("filename", "small_concert_hall_impulse_response.wav");
    model.func("int4").setIndex("funcnametable", "IR_import", 0, 0);
    model.func("int4").setIndex("fununit", "Pa", 0);
    model.func("int4").setIndex("argunit", "s", 0);

    model.sol("sol1").updateSolution();
    model.sol("sol2").updateSolution();

    model.result().duplicate("pg3", "pg2");
    model.result("pg3").feature("imp1").feature("enyd1").active(false);
    model.result("pg3").label("\u8109\u51b2\u54cd\u5e94 FFT");
    model.result("pg3").set("xlog", true);
    model.result("pg3").set("showlegends", false);
    model.result("pg3").feature("imp1").set("source", "function");
    model.result("pg3").feature("imp1").set("function", "int4");
    model.result("pg3").feature("imp1").set("funcexpr", "IR_import(t)");
    model.result("pg3").feature("imp1").set("transform", "fourier");
    model.result("pg3").feature("imp1").set("fouriershow", "spectrum");
    model.result("pg3").feature("imp1").set("scale", "multiplyperiod");
    model.result("pg3").feature("imp1").set("freqrangeactive", true);
    model.result("pg3").feature("imp1").set("freqmin", 100);
    model.result("pg3").feature("imp1").set("freqmax", 10000);
    model.result("pg3").feature("imp1").set("indb", true);
    model.result("pg3").feature("imp1").set("dbtype", "20log");
    model.result("pg3").feature("imp1").set("dbref", "manual");
    model.result("pg3").feature("imp1").set("dbmanualref", "20e-6");
    model.result("pg3").run();
    model.result("pg3").feature("imp1").set("movingaverage", true);
    model.result("pg3").feature("imp1").set("smoothtype", "octave");
    model.result("pg3").feature("imp1").set("noctave", 3);
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("\u5ea7\u6905\u58f0\u538b\u7ea7");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").set("showlegendsunit", true);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "rac.wall8.spl1.Lp");
    model.result("pg4").feature("surf1").set("descr", "\u58f0\u538b\u7ea7");
    model.result("pg4").feature("surf1").set("unit", "dB");
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").label("\u53cd\u5c04\u56fe");
    model.result("pg5").set("titletype", "label");
    model.result("pg5").set("xlabelactive", true);
    model.result("pg5").set("xlabel", "t (s)");
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("ylabel", "log10(Power)");
    model.result("pg5").set("data", "none");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg5").set("ylog", true);
    model.result("pg5").create("rtp1", "Ray1D");
    model.result("pg5").feature("rtp1").set("markerpos", "datapoints");
    model.result("pg5").feature("rtp1").set("linewidth", "preference");
    model.result("pg5").feature("rtp1").set("data", "re2");
    model.result("pg5").feature("rtp1").set("expr", "re1dist*rac.Q/re1vol");
    model.result("pg5").feature("rtp1").set("linestyle", "none");
    model.result("pg5").feature("rtp1").set("linemarker", "point");
    model.result("pg5").feature("rtp1").set("legendmethod", "manual");
    model.result("pg5").feature("rtp1").set("legend", true);
    model.result("pg5").feature("rtp1").setIndex("legends", "f<sub>c</sub> = 125 Hz", 0);
    model.result("pg5").feature().duplicate("rtp2", "rtp1");
    model.result("pg5").feature("rtp2").set("data", "re3");
    model.result("pg5").feature("rtp2").setIndex("legends", "f<sub>c</sub> = 8000 Hz", 0);
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").label("T60 - \u4f30\u8ba1\u4e0e\u6a21\u578b");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").setIndex("looplevelinput", "first", 0);
    model.result("pg6").set("titletype", "label");
    model.result("pg6").set("xlabelactive", true);
    model.result("pg6").set("xlabel", "f<sub>c</sub> (Hz)");
    model.result("pg6").set("axislimits", true);
    model.result("pg6").set("xmin", 120);
    model.result("pg6").set("xmax", 8322);
    model.result("pg6").set("ymin", 0);
    model.result("pg6").set("ymax", 1.6);
    model.result("pg6").set("xlog", true);
    model.result("pg6").set("legendpos", "lowerleft");
    model.result("pg6").create("glob1", "Global");
    model.result("pg6").feature("glob1").set("markerpos", "datapoints");
    model.result("pg6").feature("glob1").set("linewidth", "preference");
    model.result("pg6").feature("glob1").setIndex("expr", "T60_S", 0);
    model.result("pg6").feature("glob1").setIndex("expr", "T60_Sna", 1);
    model.result("pg6").feature("glob1").setIndex("expr", "T60_E", 2);
    model.result("pg6").feature("glob1").set("xdatasolnumtype", "level2");
    model.result("pg6").feature("glob1").set("linemarker", "star");
    model.result("pg6").create("tblp1", "Table");
    model.result("pg6").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg6").feature("tblp1").set("linewidth", "preference");
    model.result("pg6").feature("tblp1").set("xaxisdata", 1);
    model.result("pg6").feature("tblp1").set("plotcolumninput", "manual");
    model.result("pg6").feature("tblp1").set("plotcolumns", new int[]{8});
    model.result("pg6").feature("tblp1").set("linemarker", "circle");
    model.result("pg6").feature("tblp1").set("legend", true);
    model.result("pg6").feature("tblp1").set("legendsuffix", " - \u5c04\u7ebf\u58f0\u5b66");
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").label("\u5b9a\u4e49");
    model.result("pg7").set("data", "dset2");
    model.result("pg7").setIndex("looplevelinput", "first", 0);
    model.result("pg7").set("titletype", "label");
    model.result("pg7").set("xlabelactive", true);
    model.result("pg7").set("xlabel", "f<sub>c</sub> (Hz)");
    model.result("pg7").set("ylabelactive", true);
    model.result("pg7").set("ylabel", "D (%)");
    model.result("pg7").set("axislimits", true);
    model.result("pg7").set("xmin", 120);
    model.result("pg7").set("xmax", 8322);
    model.result("pg7").set("ymin", 0);
    model.result("pg7").set("ymax", 100);
    model.result("pg7").set("xlog", true);
    model.result("pg7").set("legendpos", "upperleft");
    model.result("pg7").create("glob1", "Global");
    model.result("pg7").feature("glob1").set("markerpos", "datapoints");
    model.result("pg7").feature("glob1").set("linewidth", "preference");
    model.result("pg7").feature("glob1").setIndex("expr", "D", 0);
    model.result("pg7").feature("glob1").setIndex("unit", "s", 0);
    model.result("pg7").feature("glob1").setIndex("descr", "\u6e05\u6670\u5ea6\u4f30\u8ba1", 0);
    model.result("pg7").feature("glob1").set("xdatasolnumtype", "level2");
    model.result("pg7").feature("glob1").set("linemarker", "star");
    model.result("pg7").create("tblp1", "Table");
    model.result("pg7").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg7").feature("tblp1").set("linewidth", "preference");
    model.result("pg7").feature("tblp1").set("xaxisdata", 1);
    model.result("pg7").feature("tblp1").set("plotcolumninput", "manual");
    model.result("pg7").feature("tblp1").set("plotcolumns", new int[]{2});
    model.result("pg7").feature("tblp1").set("linemarker", "circle");
    model.result("pg7").feature("tblp1").set("legend", true);
    model.result("pg7").feature("tblp1").set("legendsuffix", " - \u5c04\u7ebf\u58f0\u5b66");
    model.result("pg7").run();
    model.result().duplicate("pg8", "pg7");
    model.result("pg8").label("\u660e\u6670\u5ea6");
    model.result("pg8").set("ylabel", "C80 (dB)");
    model.result("pg8").set("xmin", 120);
    model.result("pg8").set("xmax", 8322);
    model.result("pg8").set("ymin", -1);
    model.result("pg8").set("ymax", 10);
    model.result("pg8").feature("glob1").setIndex("expr", "C80", 0);
    model.result("pg8").feature("glob1").setIndex("descr", "\u660e\u6670\u5ea6 80 \u4f30\u8ba1", 0);
    model.result("pg8").feature("tblp1").set("plotcolumns", new int[]{3});
    model.result("pg8").run();
    model.result().duplicate("pg9", "pg8");
    model.result("pg9").label("\u4e2d\u5fc3\u65f6\u95f4");
    model.result("pg9").set("ylabel", "ts (s)");
    model.result("pg9").set("xmin", 120);
    model.result("pg9").set("xmax", 8322);
    model.result("pg9").set("ymin", 0);
    model.result("pg9").set("ymax", 0.15);
    model.result("pg9").set("legendpos", "lowerleft");
    model.result("pg9").feature("glob1").setIndex("expr", "ts", 0);
    model.result("pg9").feature("glob1").setIndex("unit", "", 0);
    model.result("pg9").feature("glob1").setIndex("descr", "\u4e2d\u5fc3\u65f6\u95f4\u4f30\u8ba1", 0);
    model.result("pg9").feature("tblp1").set("plotcolumns", new int[]{4});
    model.result("pg9").run();
    model.result().duplicate("pg10", "pg9");
    model.result("pg10").label("\u6df7\u54cd\u65f6\u95f4");
    model.result("pg10").set("ylabel", "RT (s)");
    model.result("pg10").set("xmin", 120);
    model.result("pg10").set("xmax", 8322);
    model.result("pg10").set("ymax", 1.6);
    model.result("pg10").feature().remove("glob1");
    model.result("pg10").feature("tblp1").set("plotcolumns", new int[]{5, 6, 7, 8});
    model.result("pg10").run();
    model.result().duplicate("pg11", "pg10");
    model.result("pg11").label("\u8bed\u97f3\u4f20\u8f93\u6307\u6570");
    model.result("pg11").set("ylabel", "STI (1)");
    model.result("pg11").set("xmin", 120);
    model.result("pg11").set("xmax", 8322);
    model.result("pg11").set("ymax", 1);
    model.result("pg11").set("legendpos", "upperleft");
    model.result("pg11").feature("tblp1").set("plotcolumns", new int[]{9});
    model.result("pg11").run();

    model.nodeGroup().create("grp1", "Results");
    model.nodeGroup("grp1").set("type", "plotgroup");
    model.nodeGroup("grp1").placeAfter("plotgroup", "pg6");
    model.nodeGroup("grp1").add("plotgroup", "pg7");
    model.nodeGroup("grp1").add("plotgroup", "pg8");
    model.nodeGroup("grp1").add("plotgroup", "pg9");
    model.nodeGroup("grp1").add("plotgroup", "pg10");
    model.nodeGroup("grp1").add("plotgroup", "pg11");
    model.nodeGroup("grp1").label("\u76ee\u6807\u8d28\u91cf\u6307\u6807\u56fe");

    model.study().create("std2");
    model.study("std2").create("rtrac", "RayTracing");
    model.study("std2").feature("rtrac").setSolveFor("/physics/rac", true);
    model.study("std2").label("\u7814\u7a76 2 - \u5b9a\u5411\u626c\u58f0\u5668");
    model.study("std2").feature("rtrac").set("tunit", "s");
    model.study("std2").feature("rtrac").set("tlist", "0 1.6");
    model.study("std2").feature("rtrac").set("useadvanceddisable", true);
    model.study("std2").feature("rtrac").set("disabledphysics", new String[]{"rac/swd1"});
    model.study("std2").create("param", "Parametric");
    model.study("std2").feature("param").setIndex("pname", "alpha0", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "rad", 0);
    model.study("std2").feature("param").setIndex("pname", "alpha0", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "rad", 0);
    model.study("std2").feature("param").setIndex("pname", "f0", 0);
    model.study("std2").feature("param").setIndex("punit", "Hz", 0);
    model.study("std2").feature("param").setIndex("plistarr", "{63, 125, 250, 500, 1e3, 2e3, 4e3}", 0);
    model.study("std2").createAutoSequences("all");

    model.sol().create("sol11");
    model.sol("sol11").study("std2");
    model.sol("sol11").label("\u53c2\u6570\u5316\u89e3 2");

    model.batch("p2").feature("so1").set("psol", "sol11");
    model.batch("p2").run("compute");

    model.result().dataset().create("ray2", "Ray");
    model.result().dataset("ray2").set("solution", "sol11");
    model.result().dataset("ray2").set("posdof", new String[]{"comp1.qx", "comp1.qy", "comp1.qz"});
    model.result().dataset("ray2").set("geom", "geom1");
    model.result().dataset("ray2").set("rgeom", "pgeom_rac");
    model.result().dataset("ray2").set("rgeomspec", "fromphysics");
    model.result().dataset("ray2").set("physicsinterface", "rac");
    model.result().create("pg12", "PlotGroup3D");
    model.result("pg12").set("data", "ray2");
    model.result("pg12").setIndex("looplevel", 2, 0);
    model.result("pg12").setIndex("looplevel", 7, 1);
    model.result("pg12").label("\u5c04\u7ebf\u8f68\u8ff9 (rac) 1");
    model.result("pg12").set("showlegendsunit", true);
    model.result("pg12").create("rtrj1", "RayTrajectories");
    model.result("pg12").feature("rtrj1").set("linetype", "line");
    model.result("pg12").feature("rtrj1").create("col1", "Color");
    model.result("pg12").feature("rtrj1").feature("col1").set("expr", "rac.I");
    model.result("pg12").feature("rtrj1").create("filt1", "RayTrajectoriesFilter");
    model.result().dataset().create("re4", "Receiver3D");
    model.result().dataset("re4").set("data", "ray2");
    model.result().dataset("re4").set("receiverselection", "comp1.rac.rec1");
    model.result("pg12").set("boxlengths", new double[]{11, 10, 7.460579422167752});
    model.result("pg12").feature("rtrj1").set("sphereradiusscale", 1);
    model.result("pg12").feature("rtrj1").set("sphereradiusscaleactive", false);
    model.result("pg12").feature("rtrj1").set("tailscale", 1);
    model.result("pg12").feature("rtrj1").set("tailscaleactive", false);
    model.result("pg12").feature("rtrj1").set("arrowscale", 0);
    model.result("pg12").feature("rtrj1").set("arrowscaleactive", false);
    model.result("pg12").feature("rtrj1").set("ellipsearrowscale", 1);
    model.result("pg12").feature("rtrj1").set("ellipsearrowscaleactive", false);
    model.result("pg12").feature("rtrj1").feature("col1").set("rangeunit", "W/m^2");
    model.result("pg12").feature("rtrj1").feature("col1").set("rangecolormin", 1.9733906490079246E-27);
    model.result("pg12").feature("rtrj1").feature("col1").set("rangecolormax", 6.173703699169504E9);
    model.result("pg12").feature("rtrj1").feature("col1").set("rangecoloractive", "off");
    model.result("pg12").feature("rtrj1").feature("col1")
         .set("rangeactualminmax", new double[]{1.9733906490079246E-27, 6.173703699169504E9});
    model.result("pg12").feature("rtrj1").feature("col1").set("rangeisshared", false);
    model.result("pg12").feature("rtrj1").feature("col1").set("rangeminpositive", 1.9733906490079246E-27);
    model.result("pg12").feature("rtrj1").feature("col1").set("rangedatamin", 1.9733906490079246E-27);
    model.result("pg12").feature("rtrj1").feature("col1").set("rangedatamax", 6.173703699169504E9);
    model.result("pg12").feature("rtrj1").feature("col1").set("rangedataactive", "off");
    model.result("pg12").feature("rtrj1").set("renderinfo", "1 Edge3D 1 1 4 1 Color");
    model.result("pg12").feature("rtrj1")
         .set("boxlengths", new double[]{11.358345985412598, 10.499884605407715, 37.910762786865234});
    model.result("pg12").setIndex("looplevel", "interp", 0);
    model.result("pg12").set("interp", new String[]{"1[ms]"});
    model.result("pg12").feature("rtrj1").set("linetype", "none");
    model.result("pg12").feature("rtrj1").set("pointtype", "point");
    model.result("pg12").feature("rtrj1").feature("col1").set("expr", "rac.Lp");
    model.result("pg12").run();
    model.result().create("pg13", "PlotGroup3D");
    model.result("pg13").label("\u626c\u58f0\u5668\u65b9\u5411");
    model.result("pg13").set("data", "dset3");
    model.result("pg13").selection().geom("geom1", 0);
    model.result("pg13").selection().set(11);
    model.result("pg13").create("sysp1", "CoordSysPoint");
    model.result("pg13").feature("sysp1").set("sys", "sys2");
    model.result("pg13").feature("sysp1").set("scaleactive", true);
    model.result("pg13").feature("sysp1").set("scale", 2);
    model.result("pg13").run();
    model.result().duplicate("pg14", "pg4");
    model.result("pg14").set("data", "dset4");
    model.result("pg14").run();

    model.title("\u5c0f\u578b\u97f3\u4e50\u5385\u58f0\u5b66\u5206\u6790");

    model
         .description("\u5bf9\u4e8e\u97f3\u4e50\u5385\u3001\u5ba4\u5916\u73af\u5883\uff0c\u751a\u81f3\u662f\u623f\u95f4\u6765\u8bf4\uff0c\u5728\u8bbe\u8ba1\u7ed3\u6784\u548c\u5f00\u653e\u7a7a\u95f4\u65f6\u8003\u8651\u58f0\u97f3\u8d28\u91cf\u975e\u5e38\u91cd\u8981\u3002\u5728\u9ad8\u9891\u6781\u9650\u4e0b\uff0c\u6ce2\u957f\u5c0f\u4e8e\u51e0\u4f55\u7279\u5f81\uff0c\u9488\u5bf9\u8fd9\u79cd\u60c5\u51b5\uff0c\u8fdb\u884c\u58f0\u5b66\u4eff\u771f\u6700\u597d\u7684\u65b9\u6cd5\u662f\u91c7\u7528\u5c04\u7ebf\u58f0\u5b66\u3002\n\n\u672c\u6559\u7a0b\u6f14\u793a\u4f7f\u7528\u201c\u5c04\u7ebf\u58f0\u5b66\u201d\u7269\u7406\u573a\u63a5\u53e3\u5efa\u7acb\u6a21\u578b\u7684\u57fa\u672c\u6b65\u9aa4\u548c\u539f\u5219\u3002\u8be5\u6a21\u578b\u5206\u6790\u4e00\u4e2a\u5c0f\u578b\u97f3\u4e50\u5385\u7684\u58f0\u5b66\u7279\u6027\u3002\u6a21\u578b\u8bbe\u7f6e\u5305\u62ec\u5168\u5411\u58f0\u6e90\u3001\u7528\u4e8e\u955c\u9762\u6563\u5c04\u548c\u6f2b\u6563\u5c04\u7684\u58c1\u8fb9\u754c\u6761\u4ef6\u3001\u58f0\u538b\u8ba1\u7b97\u3001\u7528\u4e8e\u8109\u51b2\u54cd\u5e94\u56fe\u7684\u63a5\u6536\u5668\u6570\u636e\u96c6\u4ee5\u53ca\u80fd\u91cf\u53cd\u5c04\u56fe\u3002\u5176\u4e2d\u5c06\u5206\u6790\u7ed3\u679c\u4e0e\u7b80\u5355\u6df7\u54cd\u65f6\u95f4\u4f30\u8ba1\u503c\u8fdb\u884c\u6bd4\u8f83\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();
    model.sol("sol8").clearSolutionData();
    model.sol("sol9").clearSolutionData();
    model.sol("sol10").clearSolutionData();
    model.sol("sol11").clearSolutionData();
    model.sol("sol12").clearSolutionData();
    model.sol("sol13").clearSolutionData();
    model.sol("sol14").clearSolutionData();
    model.sol("sol15").clearSolutionData();
    model.sol("sol16").clearSolutionData();
    model.sol("sol17").clearSolutionData();
    model.sol("sol18").clearSolutionData();

    model.label("small_concert_hall.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
